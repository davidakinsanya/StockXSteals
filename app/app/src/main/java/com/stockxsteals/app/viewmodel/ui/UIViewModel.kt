package com.stockxsteals.app.viewmodel.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
      AppScreens.Login.route,
      AppScreens.Premium.route
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

  fun loginScreenTopSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> {
        90.dp
      }
      WindowType.Large -> {
        0.dp
      }
      else -> { 50.dp }
    }
    return dp
  }

  fun loginScreenBottomLarge(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 80.dp }
      else -> { 0.dp }
    }
    return dp
  }

  @SuppressLint("ModifierFactoryExtensionFunction")
  fun fillMaxSizeLarge(windowSize: WindowSize): Modifier {
    val modifier: Modifier = when (windowSize.width) {
      WindowType.Large -> { Modifier.fillMaxSize() }
      else -> { Modifier }
    }
    return modifier
  }

  @SuppressLint("ModifierFactoryExtensionFunction")
  fun loginScreenImageModifier(windowSize: WindowSize): Modifier {
    val modifier: Modifier = when (windowSize.width) {
      WindowType.Small -> {
        Modifier
          .size(150.dp)
      }
      WindowType.Large -> {
        Modifier.fillMaxSize(0.4f)
      }
      else -> {
        Modifier
          .fillMaxWidth(0.8f)
          .fillMaxHeight(0.5f)
      }
    }
    return modifier
  }

  @SuppressLint("ModifierFactoryExtensionFunction")
  fun googleLogoImageModifier(windowSize: WindowSize): Modifier {
    val modifier: Modifier = when(windowSize.width) {
      WindowType.Small -> {
        Modifier
          .size(40.dp)
          .padding(start = 5.dp, end = 10.dp)
      }
      else -> {
        Modifier
          .fillMaxSize(0.15f)
          .padding(end = 10.dp)
      }
    }
    return modifier
  }

  fun signInTextEndPaddingSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 6.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun signInTextFontSizeSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 11.sp }
      else -> { 16.sp }
    }
    return sp
  }

  fun loginScreenImagePadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 30.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun trendsGridViewSmallPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 30.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun trendsGridViewContentPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 50.dp }
      else -> { 16.dp }
    }
    return dp
  }

  fun trendsGridViewTopPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 0.dp }
      else -> { 30.dp }
    }
    return dp
  }

  fun searchGridViewSmallPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 30.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun searchGridSidesPaddingLarge(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 25.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun searchGridTopPaddingLarge(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 20.dp }
      else -> { 10.dp }
    }
    return dp
  }

  fun filterButtonWidthSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 80.dp }
      WindowType.Large -> { 120.dp }
      else -> { 90.dp }
    }
    return dp
  }

  fun filterButtonTextWidthSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 8.sp }
      WindowType.Large -> { 12.sp }
      else -> { 10.sp }
    }
    return sp
  }

  fun filterButtonSpaceBetweenLarge(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 15.dp }
      else -> { 5.dp }
    }
    return dp
  }

  fun backButtonStartPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 10.dp }
      else -> { 30.dp }
    }
    return dp
  }

  fun sizeTextFieldSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 13.sp }
      else -> { 14.sp }
    }
    return sp
  }

  fun filterTextFieldMaxWidth(windowSize: WindowSize, selected: String): Float {
    return if (selected == "Size" && windowSize.width == WindowType.Small) 0.4f else 0.5f
  }

  fun sneakersFontSizeSmall(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 22.sp }
      else -> { 25.sp }
    }
    return sp
  }

  fun searchEntryEndPaddingLarge(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 75.dp }
      else -> { 30.dp }
    }
    return dp
  }

  fun searchEntryTextWidthSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 150.dp }
      else -> { 200.dp }
    }
    return dp
  }

  fun alternativeEntryLeftBoxes(windowSize: WindowSize): List<Dp> {
    val dp: List<Dp> = when(windowSize.width) {
      WindowType.Small -> {
        listOf(40.dp, 70.dp, 95.dp)
      }
      WindowType.Large -> {
        listOf(200.dp, 290.dp, 400.dp)
      }
      else -> {
        listOf(100.dp, 145.dp, 200.dp)
      }
    }
    return dp
  }

  fun settingsPaddingSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 50.dp }
      else -> { 80.dp }
    }
    return dp
  }

  fun settingsSearchPaddingSmall(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 10.dp }
      else -> { 25.dp }
    }
    return dp
  }

  fun pagerTopRowTextWidth(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 110.dp }
      else -> { 150.dp }
    }
    return dp
  }

  fun pagerTopRowFontSize(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 10.sp }
      else -> { 15.sp }
    }
    return sp
  }

  fun settingIconSpacerPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 230.dp }
      else -> { 0.dp }
    }
    return dp
  }

  fun settingEndPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 75.dp }
      WindowType.Small -> { 30.dp }
      else -> { 10.dp }
    }
    return dp
  }

  fun settingSocialIconEndPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 255.dp }
      WindowType.Small -> { 15.dp }
      else -> { 35.dp }
    }
    return dp
  }

  fun pagerTopRowFontSizeTwo(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 8.sp }
      else -> { 12.sp }
    }
    return sp
  }

  fun pagerTopRowTextWidthTwo(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 125.dp }
      else -> { 145.dp }
    }
    return dp
  }

  fun additionalPagerDataTopPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 105.dp }
      else -> { 150.dp }
    }
    return dp
  }

  fun additionalPagerDataSmallText(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 8.sp }
      else -> { 14.sp }
    }
    return sp
  }

  fun additionalPagerDataPaddingList(windowSize: WindowSize): List<Dp> {
    val dp: List<Dp> = when(windowSize.width) {
      WindowType.Small -> { listOf(5.dp, 5.dp, 25.dp, 25.dp) }
      else -> { listOf(15.dp, 10.dp, 25.dp, 25.dp) }
    }
    return dp
  }

  fun additionalPagerDataPaddingListDisclaimer(windowSize: WindowSize): List<Dp> {
    val dp: List<Dp> = when(windowSize.width) {
      WindowType.Small -> { listOf(8.dp, 5.dp, 25.dp, 25.dp) }
      else -> { listOf(35.dp, 5.dp, 25.dp, 25.dp) }
    }
    return dp
  }

  fun additionalPagerDataInfoFontSize(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 8.sp }
      else -> { 16.sp }
    }
    return sp
  }

  fun additionalPagerDataHeadersFontSize(windowSize: WindowSize): TextUnit {
    val sp: TextUnit = when(windowSize.width) {
      WindowType.Small -> { 14.sp }
      else -> { 20.sp }
    }
    return sp
  }

  fun trendsGridExpandButtonPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 105.dp }
      WindowType.Large -> { 330.dp }
      else -> { 150.dp }
    }
    return dp
  }

  fun trendsGridImageSize(windowSize: WindowSize): Float {
    return if (windowSize.width == WindowType.Large) 0.45f else 0.65f
  }

  fun trendsGridTextPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 40.dp }
      else -> { 20.dp }
    }
    return dp
  }

  @SuppressLint("ModifierFactoryExtensionFunction")
  fun premiumScreenMainBodyModifier(windowSize: WindowSize): Modifier {
    val modifier: Modifier = when (windowSize.width) {
      WindowType.Small -> { Modifier.fillMaxHeight(0.6f).padding(top = 10.dp) }
      WindowType.Large -> { Modifier.padding(start = 60.dp, end = 60.dp) }
      else -> { Modifier.padding(top = 10.dp) }
    }
    return modifier
  }

  fun updateScreenStartPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Small -> { 10.dp }
      WindowType.Large -> { 100.dp }
      else -> { 20.dp }
    }
    return dp
  }

  fun updateScreenEndPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 150.dp }
      else -> { 10.dp }
    }
    return dp
  }

  fun updateScreenDateBottomPadding(windowSize: WindowSize): Dp {
    val dp: Dp = when(windowSize.width) {
      WindowType.Large -> { 45.dp }
      WindowType.Small -> { 10.dp }
      else -> { 25.dp }
    }
    return dp
  }
}