package com.yakisan.calculator.domain

data class CalculatorState(
    var number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperation? = null
)