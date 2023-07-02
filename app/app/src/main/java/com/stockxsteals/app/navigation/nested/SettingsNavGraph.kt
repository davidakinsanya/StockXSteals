package com.stockxsteals.app.navigation.nested

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.utils.rightEnterTransition
import com.stockxsteals.app.utils.rightExitTransition
import com.stockxsteals.app.view.compnents.settings.SettingScreen
import com.stockxsteals.app.view.compnents.settings.SettingsSplashScreen
import com.stockxsteals.app.viewmodel.ui.SettingViewModel
import com.stockxsteals.app.viewmodel.ui.TrendsUIViewModel
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsNavGraph(
    navController: NavHostController,
    settingModel: SettingViewModel,
    trendsModel: TrendsUIViewModel,
    uiModel: UIViewModel,
    windowSize: WindowSize
) {
  navigation(startDestination = AppScreens.Settings.route,
             route = "settings_route") {

    composable(route = AppScreens.Settings.route,
               exitTransition = rightExitTransition) {
      SettingsSplashScreen(navController = navController,
                           trendsModel = trendsModel,
                           uiModel = uiModel,
                           settingModel = settingModel,
                           windowSize = windowSize)
    }

    composable(route = AppScreens.SettingScreen.route,
               enterTransition = rightEnterTransition) {
      val setting = navController
        .previousBackStackEntry
        ?.savedStateHandle
        ?.get<String>("setting")

      if (setting != null)
        SettingScreen(setting = setting,
                      navController = navController,
                      settingModel = settingModel,
                      trendsModel = trendsModel,
                      uiModel = uiModel,
                      windowSize = windowSize)
    }

  }

}