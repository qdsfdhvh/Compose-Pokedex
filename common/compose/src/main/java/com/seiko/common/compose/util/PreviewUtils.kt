package com.seiko.common.compose.util

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.seiko.common.compose.theme.ComposePokedexTheme

@Composable
fun ThemedPreview(
  darkTheme: Boolean = false,
  content: @Composable () -> Unit
) {
  ComposePokedexTheme(darkTheme = darkTheme) {
    Surface {
      content()
    }
  }
}