package com.view.finance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.view.finance.domain.model.Transaction
import com.view.finance.data.local.converters.Converters

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
 @TypeConverters(Converters::class)
abstract class FinanceDatabase : RoomDatabase() {

     abstract fun transactionDao(): TransactionDao
}