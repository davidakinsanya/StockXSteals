package com.stockxsteals.app.viewmodel

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.stockxsteals.app.navigation.AppScreens

class UIViewModel {

  fun listOfScreens(): List<AppScreens> {
    return listOf(
      AppScreens.Trends,
      AppScreens.Search,
      AppScreens.Settings,
    )
  }

  fun selectedIsSearch(selected: String): Boolean {
    return selected == "Search"
  }

  fun searchIsFilterOrSneakerScreen(selected: String, curr: NavDestination?): Boolean {
    return this.selectedIsSearch(selected) ||
            curr?.route == "top_search" ||
            curr?.route == "sneaker_search"
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


}