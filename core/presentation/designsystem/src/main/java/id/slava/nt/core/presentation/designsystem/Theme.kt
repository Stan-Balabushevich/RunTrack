package id.slava.nt.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = PlrunBlue,
    background = PlrunBlack,
    surface = PlrunDarkGray,
    secondary = PlrunWhite,
    tertiary = PlrunWhite,
    primaryContainer = PlrunBlue30,
    onPrimary = PlrunBlack,
    onBackground = PlrunWhite,
    onSurface = PlrunWhite,
    onSurfaceVariant = PlrunGray,
    error = PlrunDarkRed,
    errorContainer = PlrunDarkRed5
)

@Composable
fun PlrunTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}