package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.view.compnents.main.SneakerViewComponent
import com.stockxsteals.app.view.compnents.search.SearchScreen
import com.stockxsteals.app.view.compnents.settings.SettingsSplashScreen
import com.stockxsteals.app.view.compnents.sneakers.SneakerSplashScreen
import com.stockxsteals.app.viewmodel.FilterViewModel

@Composable
fun NavGraph(navController: NavHostController, filterModel: FilterViewModel) {
  NavHost(navController = navController, startDestination = AppScreens.Trends.route) {
    composable(route = AppScreens.Trends.route) {
      SneakerViewComponent()
    }
    composable(route = AppScreens.Search.route) {
      SneakerViewComponent()
    }

    composable(route = AppScreens.Settings.route) {
      SettingsSplashScreen(navController = navController)
    }

    composable(route = AppScreens.TopSearch.route) {
      SearchScreen(navController = navController, model = filterModel)
    }

    composable(route = AppScreens.SneakerSearch.route) {
      SneakerSplashScreen(navController = navController, model = filterModel)
    }
  }
}