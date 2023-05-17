package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.settings.SettingScreen
import com.stockxsteals.app.view.compnents.settings.SettingsSplashScreen

fun NavGraphBuilder.settingsNavGraph(
  navController: NavHostController
) {
  navigation(startDestination = AppScreens.Settings.route,
             route = "settings_route") {

    composable(route = AppScreens.Settings.route) {
      SettingsSplashScreen(navController)
    }

    composable(route = AppScreens.SettingScreen.route) {
      val setting = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("setting")

      if (setting != null)
        SettingScreen(setting = setting, navController = navController)
    }

  }

}