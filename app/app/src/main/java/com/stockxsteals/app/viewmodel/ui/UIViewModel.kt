package com.stockxsteals.app.viewmodel.ui

import androidx.compose.runtime.State
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.navigation.AppScreens

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

  fun purpleSearchBar(selected: String, curr: NavDestination?): Boolean {
    return this.selectedIsSearch(selected) ||
            curr?.route == "top_search" ||
            curr?.route == "sneaker_search" ||
            curr?.route == "trends"
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

  fun productIsNotNull(product: State<Product>?): Boolean {
    return product != null
  }
}