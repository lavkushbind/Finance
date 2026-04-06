package com.view.finance.data.repository

import com.view.finance.data.local.TransactionDao
import com.view.finance.domain.model.Transaction
import com.view.finance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao
) : TransactionRepository {
    override fun getTransactions(): Flow<List<Transaction>> = dao.getAllTransactions()
    override suspend fun insertTransaction(transaction: Transaction) = dao.insertTransaction(transaction)
    override suspend fun deleteTransaction(transaction: Transaction) = dao.deleteTransaction(transaction)
}