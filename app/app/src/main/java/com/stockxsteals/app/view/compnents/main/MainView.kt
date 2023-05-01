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
import com.stockxsteals.app.viewmodel.FilterViewModel
import com.stockxsteals.app.viewmodel.UIViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupScreen(navController: NavHostController) {

  val filterModel = FilterViewModel()
  val uiModel = UIViewModel()
  val searchDestination = AppScreens.TopSearch.route
  val settingsDestination = AppScreens.Settings.route
  val sneakersDestination = AppScreens.SneakerSearch.route
  var selected = ""
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination

  uiModel.listOfScreens().forEach { _ ->
    val bool = currentDestination?.hierarchy?.any {
      it.route == searchDestination || it.route == settingsDestination || it.route == sneakersDestination
    } == true

    Scaffold(
      topBar = { SearchAppBar(navController = navController, filterModel = filterModel, uiModel = uiModel) },
      bottomBar = { if (!bool) BottomBar(navController = navController) }
    ) {
      NavGraph(navController = navController, filterModel = filterModel, uiModel = uiModel)
    }
  }
}