package com.view.finance.presentation.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.view.finance.domain.model.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showSheet = true },
                containerColor = Color(0xFF2196F3),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) { padding ->
        if (showSheet) {
            AddTransactionSheet(
                onDismiss = { showSheet = false },
                onSave = { viewModel.addTransaction(it); showSheet = false }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Zorvyn Finance", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.ExtraBold)

                 Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3))
                ) {
                    Column(modifier = Modifier.padding(32.dp)) {
                        Text("Available Balance", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.titleMedium)
                        Text("₹${uiState.totalBalance}", style = MaterialTheme.typography.displaySmall, color = Color.White, fontWeight = FontWeight.ExtraBold)

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text("Income", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.labelLarge)
                                Text("₹${uiState.totalIncome}", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("Expense", color = Color.White.copy(alpha = 0.8f), style = MaterialTheme.typography.labelLarge)
                                Text("₹${uiState.totalExpense}", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }

                 Text("Budget Tracker", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                 BudgetBar("Needs (50%)", uiState.needsSpent, 5000.0)
                BudgetBar("Wants (30%)", uiState.wantsSpent, 3000.0)
                BudgetBar("Savings (20%)", uiState.savingsSpent, 2000.0)

                Spacer(modifier = Modifier.height(24.dp))
                Text("Recent Activity", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
            }

            items(uiState.transactions) { tx ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                ) {
                    ListItem(
                        headlineContent = { Text(tx.category, fontWeight = FontWeight.SemiBold) },
                        trailingContent = {
                            Text("₹${tx.amount}", color = if(tx.type == TransactionType.INCOME) Color(0xFF4CAF50) else Color(0xFFFF5252), fontWeight = FontWeight.Bold)
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent)
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

 @Composable
fun BudgetBar(title: String, spent: Double, max: Double) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
            Text("₹${spent.toInt()}", fontWeight = FontWeight.Bold)
        }
        LinearProgressIndicator(
            progress = { (spent / max).toFloat().coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth().height(8.dp).padding(top = 4.dp).clip(RoundedCornerShape(4.dp)),
            color = Color(0xFF2196F3),
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}