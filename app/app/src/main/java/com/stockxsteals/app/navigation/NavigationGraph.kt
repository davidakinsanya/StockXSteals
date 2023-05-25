package com.stockxsteals.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stockxsteals.app.navigation.nested.loginGraph
import com.stockxsteals.app.navigation.nested.settingsNavGraph
import com.stockxsteals.app.navigation.nested.sneakerNavGraph
import com.stockxsteals.app.viewmodel.ui.*

@Composable
fun NavGraph(navController: NavHostController,
             productSearchViewModel: ProductSearchViewModel,
             settingModel: SettingViewModel,
             trendsModel: TrendsViewModel,
             networkModel: NetworkViewModel
) {
  NavHost(navController = navController,
          startDestination = "login_route",
          route = "root_route") {

    loginGraph(navController, networkModel)
    sneakerNavGraph(trendsModel, productSearchViewModel, networkModel, navController)
    settingsNavGraph(navController, settingModel)
  }
}