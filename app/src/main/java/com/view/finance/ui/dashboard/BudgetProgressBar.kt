package com.view.finance.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BudgetStatusCard(needs: Double, wants: Double, savings: Double) {
    Card(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("50/30/20 Budget Status", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

             BudgetRow("Needs (50%)", needs)
            BudgetRow("Wants (30%)", wants)
            BudgetRow("Savings (20%)", savings)
        }
    }
}

@Composable
fun BudgetRow(label: String, amount: Double) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label)
        Text("₹$amount")
    }
}