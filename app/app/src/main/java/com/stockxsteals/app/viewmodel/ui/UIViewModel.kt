package com.stockxsteals.app.viewmodel.ui

import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.utils.WindowType

class UIViewModel: ViewModel() {

  fun listOfScreens(): List<AppScreens> {
    return listOf(
      AppScreens.Trends,
      AppScreens.Search,
      AppScreens.Settings,
    )
  }

  fun bottomNavBool(): List<String> {
    return listOf(
      AppScreens.TopSearch.route,
      AppScreens.Settings.route,
      AppScreens.SneakerSearch.route,
      AppScreens.SettingScreen.route,
      AppScreens.Login.route
    )
  }

  fun selectedIsSearch(selected: String): Boolean {
    return selected == "Search"
  }

  fun selectedIsTrend(selected: String): Boolean {
    return selected == "Trends"
  }

  fun purpleSearchBar(curr: NavDestination?): Boolean {
    return  curr?.route == "trends" ||
            curr?.route == "search" ||
            curr?.route == "top_search" ||
            curr?.route == "sneaker_search" ||
            curr?.route == null
  }

  fun resetTextField(curr: NavDestination?): Boolean {
    return curr?.route == "search" ||
            curr?.route == "sneaker_search"
  }

  fun textIsEmpty(text: String): Boolean {
    return text.isEmpty()
  }

  @OptIn(ExperimentalComposeUiApi::class)
  fun nextPressBackSpace(it: KeyEvent): Boolean {
    return it.key == Key.Backspace
  }

  fun listOfChips(): List<String> {
    return listOf("Country", "Currency", "Size")
  }

  fun previousScreenSneaker(navController: NavHostController): Boolean {
    return navController.previousBackStackEntry?.destination?.route!! != "sneaker_search"
  }

  fun countryFilled(text: String): Boolean {
    return text.length >= 2
  }

  fun selectedIsCountry(selected: String): Boolean {
    return selected == "Country"
  }

  fun progressCheck(progressCount: Int): Boolean {
    return progressCount < 4
  }

  fun sizeModifier(size: Double): String {
    return if (size.toString().contains(".0")) size.toInt().toString()
    else size.toString()
  }

  fun productIsNotNull(product: State<Any>?): Boolean {
    return product != null
  }

  fun trendsGridViewSmallPadding(windowSize: WindowSize): Dp {
    var dp: Dp = 0.dp
    when(windowSize.width) {
      WindowType.Small -> {
        dp = 30.dp
      }
      else -> {}
    }
    return dp
  }

  fun searchGridViewSmallPadding(windowSize: WindowSize): Dp {
    var dp: Dp = 0.dp
    when(windowSize.width) {
      WindowType.Small -> {
        dp = 30.dp
      }
      else -> {}
    }
    return dp
  }

  fun filterButtonWidthSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> {
        80.dp
      }
      else -> {
        90.dp
      }
    }
    return dp
  }

  fun filterButtonTextWidthSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> {
        8.sp
      }
      else -> {
        10.sp
      }
    }
    return sp
  }

  fun backButtonStartPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> {
        10.dp
      }
      else -> {
        30.dp
      }
    }
    return dp
  }

  fun sizeTextFieldSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> {
        13.sp
      }
      else -> {
        14.sp
      }
    }
    return sp
  }

  fun filterTextFieldMaxWidth(windowSize: WindowSize, selected: String): Float {
    return if (selected == "Size" && windowSize.width == WindowType.Small) 0.4f else 0.5f
  }
}