package com.stockxsteals.app.view.compnents.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stockxsteals.app.navigation.AppScreens
import com.stockxsteals.app.navigation.NavGraph
import com.stockxsteals.app.viewmodel.db.*
import com.stockxsteals.app.viewmodel.ui.*
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupScreen(navController: NavHostController) {


  val uiModel: UIViewModel = getViewModel()
  val networkModel: NetworkViewModel = getViewModel()
  val settingModel: SettingViewModel = getViewModel()
  val trendsModel: TrendsUIViewModel = getViewModel()
  val productSearchModel: ProductSearchViewModel = getViewModel()


  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  uiModel.listOfScreens().forEach { _ ->
    val bool = currentDestination?.hierarchy?.any {
      uiModel.bottomNavBool().contains(it.route)
    } == true

    val isNull = currentDestination?.route == null
    val isLogin = currentDestination?.route == AppScreens.Login.route


    Scaffold(
      topBar = { if (!isLogin && !isNull) SearchAppBar(navController = navController,
                                                       productSearchViewModel = productSearchModel,
                                                       trendsModel = trendsModel,
                                                       uiModel = uiModel,
                                                       networkModel = networkModel) },

      bottomBar = { if (!bool) BottomBar(navController = navController,
                                         trendsModel = trendsModel) }
    ) {
      NavGraph(
        navController = navController,
        productSearchViewModel = productSearchModel,
        settingModel = settingModel,
        trendsModel = trendsModel,
        networkModel = networkModel)
    }
  }
}