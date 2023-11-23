package com.yakisan.calculator.model

import java.time.LocalDateTime

data class History(
    val date: LocalDateTime,
    val value: String,
    val result: String,
)