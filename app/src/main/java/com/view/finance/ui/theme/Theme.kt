package com.view.finance.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

 private val MyLightColors = lightColorScheme(
    primary = BlueMain,
    onPrimary = Color.White,
    secondary = BlueLight,
    background = Color(0xFFF8F9FB),
    surface = Color.White,
    onSurface = Black
)

 private val MyDarkColors = darkColorScheme(
    primary = BlueMain,
    onPrimary = Color.White,
    secondary = BlueLight,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White
)

@Composable
fun FinanceCompanionTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) MyDarkColors else MyLightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}