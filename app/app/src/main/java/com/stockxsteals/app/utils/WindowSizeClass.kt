package com.stockxsteals.app.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

data class WindowSize(
  val width: WindowType,
  val height: WindowType
)

enum class WindowType { Small, Compact, Medium, Large }

@Composable
fun rememberWindowSize(): WindowSize {
  val configuration = LocalConfiguration.current
  val screenWidth by remember(key1 = configuration) {
    mutableStateOf(configuration.screenWidthDp)
  }
  val screenHeight by remember(key1 = configuration) {
    mutableStateOf(configuration.screenHeightDp)
  }

  return WindowSize(
    width = getScreenWidth(screenWidth),
    height = getScreenHeight(screenHeight)
  )
}

fun getScreenWidth(width: Int): WindowType = when {
  width <= 360 -> WindowType.Small
  width in 361..480 -> WindowType.Compact
  width in 481..720 -> WindowType.Medium
  else -> WindowType.Large
}

fun getScreenHeight(height: Int): WindowType = when {
  height <= 360 -> WindowType.Small
  height in 361..480 -> WindowType.Compact
  height in 481..720 -> WindowType.Medium
  else -> WindowType.Large
}
