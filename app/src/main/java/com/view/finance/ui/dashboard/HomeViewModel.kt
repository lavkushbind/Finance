package com.view.finance.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.view.finance.domain.model.Transaction
import com.view.finance.domain.model.TransactionType
import com.view.finance.domain.repository.TransactionRepository // Repo import karein
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

data class HomeUiState(
    val transactions: List<Transaction> = emptyList(),
    val totalBalance: Double = 0.0,
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val needsSpent: Double = 0.0,
    val wantsSpent: Double = 0.0,
    val savingsSpent: Double = 0.0
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TransactionRepository // Dao ki jagah Repository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = repository.getTransactions() // Repo se data le rahe hain
        .map { list ->
            val now = LocalDate.now()
            val startOfMonth = now.withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

            val currentMonthTx = list.filter { it.date >= startOfMonth }
            val income = currentMonthTx.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
            val expense = currentMonthTx.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

            HomeUiState(
                transactions = currentMonthTx.sortedByDescending { it.date }.take(10),
                totalBalance = income - expense,
                totalIncome = income,
                totalExpense = expense,
                needsSpent = currentMonthTx.filter { it.category.contains("Rent", true) || it.category.contains("Food", true) }.sumOf { it.amount },
                wantsSpent = currentMonthTx.filter { it.category.contains("Shopping", true) }.sumOf { it.amount },
                savingsSpent = currentMonthTx.filter { it.category.contains("Investment", true) }.sumOf { it.amount }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState()
        )

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch { repository.insertTransaction(transaction) }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch { repository.deleteTransaction(transaction) }
    }
}