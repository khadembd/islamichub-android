package com.islamichub.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val IHColorScheme = darkColorScheme(
    primary = IHColors.Primary,
    onPrimary = IHColors.White,
    primaryContainer = IHColors.PrimaryDark,
    onPrimaryContainer = IHColors.White,
    secondary = IHColors.Accent,
    onSecondary = IHColors.White,
    background = IHColors.BG,
    onBackground = IHColors.White,
    surface = IHColors.Surface,
    onSurface = IHColors.White,
    surfaceVariant = IHColors.SurfaceLight,
    onSurfaceVariant = IHColors.TextSecondary,
    error = IHColors.Error,
    outline = IHColors.Divider,
    scrim = IHColors.Black,
)

@Composable
fun IslamicHubTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            window.statusBarColor = IHColors.BG.toArgb()
            window.navigationBarColor = IHColors.BG.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    MaterialTheme(colorScheme = IHColorScheme, content = content)
}
