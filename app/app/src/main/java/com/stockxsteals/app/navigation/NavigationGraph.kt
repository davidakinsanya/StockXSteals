package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.view.compnents.main.SneakerViewComponent
import com.stockxsteals.app.view.compnents.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {
  NavHost(navController = navController, startDestination = AppScreens.Trends.route) {
    composable(route = AppScreens.Trends.route) {
      SneakerViewComponent()
    }
    composable(route = AppScreens.Search.route) {
      SneakerViewComponent()
    }

    composable(route = AppScreens.Settings.route) {
      SneakerViewComponent()
    }

    composable(route = AppScreens.TopSearch.route) {
      SearchScreen(navController = navController)
    }
  }
}