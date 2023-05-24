package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.navigation.nested.sneakerNavGraph
import com.stockxsteals.app.view.compnents.login.LoginScreen
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun NavGraph(navController: NavHostController,
             productSearchViewModel: ProductSearchViewModel,
             settingModel: SettingViewModel,
             trendsModel: TrendsViewModel
) {
  NavHost(navController = navController,
          startDestination = AppScreens.Login.route,
          route = "root_route") {

    composable(route = AppScreens.Login.route) {
      LoginScreen(navController = navController)
    }

    sneakerNavGraph(trendsModel, productSearchViewModel, navController)
    settingsNavGraph(navController, settingModel)
  }
}