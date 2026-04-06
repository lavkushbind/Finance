package com.view.finance.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.view.finance.presentation.dashboard.HomeScreen
import com.view.finance.presentation.goals.GoalsScreen
import com.view.finance.presentation.insights.InsightsScreen
import com.view.finance.presentation.navigation.BottomNavigationBar
import com.view.finance.presentation.navigation.Screen
import com.view.finance.presentation.transactions.TransactionListScreen
import com.view.finance.presentation.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
         bottomBar = {
            Surface(tonalElevation = 8.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)) {
                BottomNavigationBar(navController)
            }
        }
    ) { padding ->
         NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding),
            enterTransition = { fadeIn(animationSpec = tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) }
        ) {
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Transactions.route) { TransactionListScreen() }
            composable(Screen.Goals.route) { GoalsScreen() }
            composable(Screen.Insights.route) { InsightsScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}