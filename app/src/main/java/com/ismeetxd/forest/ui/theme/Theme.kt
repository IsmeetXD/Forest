package com.ismeetxd.forest.ui.theme

import android.os.Build
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDirection
import com.google.android.material.color.MaterialColors
import com.ismeetxd.forest.ui.common.LocalDynamicColorSwitch
import com.ismeetxd.forest.ui.common.LocalFixedColorRoles
import com.kyant.monet.LocalTonalPalettes
import com.kyant.monet.dynamicColorScheme

fun Color.applyOpacity(enabled: Boolean): Color {
    return if (enabled) this else this.copy(alpha = 0.62f)
}

@Composable
@ReadOnlyComposable
fun Color.harmonizeWith(other: Color) =
    Color(MaterialColors.harmonize(this.toArgb(), other.toArgb()))

@Composable
@ReadOnlyComposable
fun Color.harmonizeWithPrimary(): Color =
    this.harmonizeWith(other = MaterialTheme.colorScheme.primary)

@Composable
fun ForestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrastModeEnabled: Boolean = false,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    LaunchedEffect(darkTheme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (darkTheme) {
                view.windowInsetsController?.setSystemBarsAppearance(
                    0,
                    APPEARANCE_LIGHT_STATUS_BARS,
                )
            } else {
                view.windowInsetsController?.setSystemBarsAppearance(
                    APPEARANCE_LIGHT_STATUS_BARS,
                    APPEARANCE_LIGHT_STATUS_BARS,
                )
            }
        }
    }

    val isDynamicColor = LocalDynamicColorSwitch.current

    val colorScheme =
        dynamicColorScheme(!darkTheme).run {
            if (isHighContrastModeEnabled && darkTheme) {
                copy(
                    surface = Color.Black,
                    background = Color.Black,
                    surfaceContainerLowest = Color.Black,
                    surfaceContainerLow = surfaceContainerLowest,
                    surfaceContainer = surfaceContainerLow,
                    surfaceContainerHigh = surfaceContainerLow,
                    surfaceContainerHighest = surfaceContainer,
                )
            } else if (!isDynamicColor && darkTheme) {
                copy(
                    background = Color(0xFF272E33),
                    surface = Color(0xFF272E33),
                    surfaceContainerLowest = Color(0xFF1E2326),
                    surfaceContainerLow = Color(0xFF232A2E),
                    surfaceContainer = Color(0xFF2D353B),
                    surfaceContainerHigh = Color(0xFF343F44),
                    surfaceContainerHighest = Color(0xFF3D484D),
                    onBackground = Color(0xFFD3C6AA),
                    onSurface = Color(0xFFD3C6AA),
                    onSurfaceVariant = Color(0xFF9DA9A0),
                    primary = Color(0xFFA7C080),
                    onPrimary = Color(0xFF232A2E),
                    primaryContainer = Color(0xFF37463B),
                    onPrimaryContainer = Color(0xFFD3E7BE),
                    secondary = Color(0xFF83C092),
                    onSecondary = Color(0xFF1E2D24),
                    secondaryContainer = Color(0xFF2E4638),
                    onSecondaryContainer = Color(0xFFC0E8CA),
                    tertiary = Color(0xFFDBBC7F),
                    onTertiary = Color(0xFF382E10),
                    tertiaryContainer = Color(0xFF4C3F1B),
                    onTertiaryContainer = Color(0xFFF2DEAC),
                    outline = Color(0xFF7A8478),
                    outlineVariant = Color(0xFF414B50),
                )
            } else if (!isDynamicColor && !darkTheme) {
                copy(
                    background = Color(0xFFFDF6E3),
                    surface = Color(0xFFFDF6E3),
                    surfaceContainerLowest = Color(0xFFFFFFFF),
                    surfaceContainerLow = Color(0xFFF4EDE8),
                    surfaceContainer = Color(0xFFEFE6DD),
                    surfaceContainerHigh = Color(0xFFE8DECE),
                    surfaceContainerHighest = Color(0xFFDFD5C2),
                    onBackground = Color(0xFF5C6A72),
                    onSurface = Color(0xFF5C6A72),
                    onSurfaceVariant = Color(0xFF708089),
                    primary = Color(0xFF537841),
                    onPrimary = Color(0xFFFFFFFF),
                    primaryContainer = Color(0xFFD3E7BE),
                    onPrimaryContainer = Color(0xFF14240B),
                    secondary = Color(0xFF4F7E65),
                    onSecondary = Color(0xFFFFFFFF),
                    secondaryContainer = Color(0xFFC0E8CA),
                    onSecondaryContainer = Color(0xFF10271B),
                    tertiary = Color(0xFF8A6C28),
                    onTertiary = Color(0xFFFFFFFF),
                    tertiaryContainer = Color(0xFFF2DEAC),
                    onTertiaryContainer = Color(0xFF2B2005),
                    outline = Color(0xFF939F91),
                    outlineVariant = Color(0xFFBDC7B9),
                )
            } else this
        }

    val textStyle =
        LocalTextStyle.current.copy(
            lineBreak = LineBreak.Paragraph,
            textDirection = TextDirection.Content,
        )

    val tonalPalettes = LocalTonalPalettes.current

    CompositionLocalProvider(
        LocalFixedColorRoles provides FixedColorRoles.fromTonalPalettes(tonalPalettes),
        LocalTextStyle provides textStyle,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}


@Composable
@Deprecated("Use ForestTheme instead", replaceWith = ReplaceWith("ForestTheme(content)"))
fun PreviewThemeLight(content: @Composable () -> Unit) {
    ForestTheme(darkTheme = false, content = content)
}
