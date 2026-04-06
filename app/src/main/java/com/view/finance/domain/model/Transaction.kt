package com.view.finance.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TransactionType {
    INCOME, EXPENSE
}

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val amount: Double,

     val type: TransactionType,

    val category: String,
    val date: Long = System.currentTimeMillis(),

    val note: String = ""
)