package com.yakisan.calculator.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.yakisan.calculator.core.Constant.MAX_NUM_LENGTH
import com.yakisan.calculator.data.HistoryDao
import com.yakisan.calculator.data.HistoryEntity
import com.yakisan.calculator.domain.CalculatorAction
import com.yakisan.calculator.domain.CalculatorOperation
import com.yakisan.calculator.domain.CalculatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val historyDAO: HistoryDao
): ViewModel() {

    var state by mutableStateOf(CalculatorState())


    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> {
                val result = calculate()
                saveHistory(state.number1 + state.operation!!.symbol + state.number2, result.toString())
                state = state.copy(
                    number1 = result.toString(),
                    number2 = "",
                    operation = null
                )
            }
            is CalculatorAction.Percent -> performPercentage()
            is CalculatorAction.AddZero -> addZero()
        }
    }

    // Save history to database
    private fun saveHistory(operation: String, result: String) {
        val historyEntry = HistoryEntity(
            expression = operation,
            result = result,
            timestamp = System.currentTimeMillis()
        )
        historyDAO.insertHistory(historyEntry)
    }

    // Retrieve history from database
    fun getHistory(): List<HistoryEntity> {
        return historyDAO.getAllHistory()
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                is CalculatorOperation.Percent -> number1 * number2 / 100
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

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

    private fun performPercentage() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = number1 * number2 / 100
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterDecimal() {
        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

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