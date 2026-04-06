package com.view.finance.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Transactions : Screen("transactions", "Records", Icons.Default.List)
    object Goals : Screen("goals", "Goals", Icons.Default.CheckCircle)
    object Insights : Screen("insights", "Insights", Icons.Default.Menu)
    object Profile : Screen("profile", "Profile", Icons.Default.Person) // Yeh add karna zaroori hai
}