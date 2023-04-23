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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupScreen(navController: NavHostController) {
  val screens = listOf(
    AppScreens.Trends,
    AppScreens.Search,
    AppScreens.Settings,
  )

  val searchDestination = AppScreens.TopSearch.route
  val settingsDestination = AppScreens.Settings.route
  var selected = ""
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  screens.forEach { _ ->
    val bool = currentDestination?.hierarchy?.any {
      it.route == searchDestination || it.route == settingsDestination
    } == true

    Scaffold(
      topBar = { SearchAppBar(navController = navController)},
      bottomBar = { if (!bool) BottomBar(navController = navController) }
    ) {
      NavGraph(navController = navController, filterModel = FilterViewModel())
    }
  }
}