package com.yakisan.calculator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "dayOfMonth") val dayOfMonth: String?,
    @ColumnInfo(name = "month") val month: String?,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "time") val time: String?,
    @ColumnInfo(name = "value") val value: String?,
    @ColumnInfo(name = "result") val result: String?,
)
