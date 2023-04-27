package com.stockxsteals.app.navigation

import android.util.Log
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
      if (navController.previousBackStackEntry?.destination?.route != AppScreens.Search.route) {
        val model =
          navController.previousBackStackEntry?.savedStateHandle?.get<FilterViewModel>("filterModel")
        if (model != null)
          SearchScreen(navController = navController, model = model)
      } else {
        SearchScreen(navController = navController, model = filterModel)
      }
    }

    composable(route = AppScreens.SneakerSearch.route) {

      val model = navController.previousBackStackEntry?.savedStateHandle?.get<FilterViewModel>("filterModel")
      if (model != null)
        SneakerSplashScreen(navController = navController, model = model)

    }

  }
}