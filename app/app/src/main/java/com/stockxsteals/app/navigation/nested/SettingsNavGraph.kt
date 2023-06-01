package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.settings.SettingScreen
import com.stockxsteals.app.view.compnents.settings.SettingsSplashScreen
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel

fun NavGraphBuilder.settingsNavGraph(
  navController: NavHostController,
  settingModel: SettingViewModel,
  trendsModel: TrendsUIViewModel,
) {
  navigation(startDestination = AppScreens.Settings.route,
             route = "settings_route") {

    composable(route = AppScreens.Settings.route) {
      SettingsSplashScreen(navController = navController,
                           settingModel = settingModel,
                           trendsModel = trendsModel)
    }

    composable(route = AppScreens.SettingScreen.route) {
      val setting = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("setting")

      if (setting != null)
        SettingScreen(setting = setting,
                      navController = navController,
                      settingModel = settingModel,
                      trendsModel = trendsModel)
    }

  }

}