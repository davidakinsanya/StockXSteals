package com.stockxsteals.app.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.view.compnents.login.LoginScreen
import com.stockxsteals.app.viewmodel.ui.NetworkViewModel

fun NavGraphBuilder.loginGraph(navController: NavHostController, networkModel: NetworkViewModel) {
  navigation(startDestination = AppScreens.Login.route,
             route = "login_route") {
    composable(route = AppScreens.Login.route) {
      LoginScreen(navController = navController,
                  networkModel = networkModel)
    }
  }
}