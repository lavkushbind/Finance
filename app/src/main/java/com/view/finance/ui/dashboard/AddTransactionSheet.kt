package com.view.finance.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.view.finance.domain.model.Transaction
import com.view.finance.domain.model.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionSheet(
    onDismiss: () -> Unit,
    onSave: (Transaction) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(TransactionType.EXPENSE) }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category (e.g. Food)") },
                modifier = Modifier.fillMaxWidth()
            )

            Row {
                RadioButton(selected = type == TransactionType.INCOME, onClick = { type = TransactionType.INCOME })
                Text("Income")
                RadioButton(selected = type == TransactionType.EXPENSE, onClick = { type = TransactionType.EXPENSE })
                Text("Expense")
            }

            Button(
                onClick = {
                    val transaction = Transaction(
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        type = type,
                        category = category
                    )
                    onSave(transaction)
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Save Transaction")
            }
        }
    }
}