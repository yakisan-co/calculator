package com.yakisan.calculator.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yakisan.calculator.core.Constant.MAX_NUM_LENGTH
import com.yakisan.calculator.data.History
import com.yakisan.calculator.domain.CalculatorAction
import com.yakisan.calculator.domain.CalculatorOperation
import com.yakisan.calculator.domain.CalculatorState
import com.yakisan.calculator.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val repository: HistoryRepository
) : ViewModel() {

    var state by mutableStateOf(CalculatorState())
    var history by mutableStateOf("")

    //Add onAction (Calculator Actions) func.
    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> {
                state = CalculatorState()
                history = ""
            }

            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
            is CalculatorAction.Percent -> performPercentage()
            is CalculatorAction.AddZero -> addZero()
        }
    }

    //Enter operation (-, *, %, /, +) func.
    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    //Add general calculate func.
    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculate() {
        val number1 = state.number1.toIntOrNull()
        val number2 = state.number2.toIntOrNull()

        if (state.operation == null) {
            Log.e("CalculatorViewModel", "Operation is null in calculate()")
            return
        }

        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Percent -> number1 * number2 / 100
                null -> {
                    Log.e("CalculatorViewModel", "Unexpected null operation in calculate()")
                    return
                }
            }
            val currentDate = LocalDate.now()
            val hour = (LocalTime.now().hour).toString() + ":" +  (LocalTime.now().minute).toString()
            history = "$number1 ${state.operation?.symbol ?: ""} $number2"

            //TODO: Aylar için Türkçe dil desteği verilmeli.
            //TODO: DECEMBER -> Aralık gibi.
            viewModelScope.launch {
                repository.insertHistory(
                    history = History(
                        dayOfMonth = currentDate.dayOfMonth.toString(),
                        month = currentDate.month.toString(),
                        year = currentDate.year.toString(),
                        time = hour,
                        value = history,
                        result = result.toString()
                    )
                )
            }

            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    //Add delete func.
    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    //Add percentage func.
    private fun performPercentage() {
        val number1 = state.number1.toIntOrNull()
        val number2 = state.number2.toIntOrNull()
        if (number1 != null && number2 != null) {
            val result = number1 * number2 / 100
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    //TODO: Point fonksiyonu çalışmıyor.
    //Add decimal func.
    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    //Add double zero func.
    private fun addZero() {
        if (state.operation == null) {
            state = state.copy(
                number1 = state.number1 + "00"
            )
            return
        }
        state = state.copy(
            number2 = state.number2 + "00"
        )
    }

    //Enter number func.
    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number.toString()
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number.toString()
        )
    }

}