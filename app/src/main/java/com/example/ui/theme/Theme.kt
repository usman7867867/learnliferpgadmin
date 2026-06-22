package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AdminColorScheme = darkColorScheme(
    primary = PurpleAccent,
    onPrimary = TextWhite,
    primaryContainer = PurpleLight,
    onPrimaryContainer = DarkBlue,
    secondary = GoldAccent,
    onSecondary = DarkBlue,
    secondaryContainer = GoldDark,
    onSecondaryContainer = TextWhite,
    background = BackgroundDark,
    onBackground = TextWhite,
    surface = DarkBlueSurface,
    onSurface = TextWhite,
    surfaceVariant = DarkBlue,
    onSurfaceVariant = TextGray
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Force Dark Mode as requested
    dynamicColor: Boolean = false, // Force Premium Theme
    content: @Composable () -> Unit,
) {
    val colorScheme = AdminColorScheme

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
