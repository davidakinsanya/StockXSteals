package com.stockxsteals.app.view.compnents.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.stockxsteals.app.navigation.AppScreens

@Composable
fun BottomBar(navController: NavHostController) {
  val screens = listOf(
    AppScreens.Trends,
    AppScreens.Search,
    AppScreens.Settings,
  )

  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackStackEntry?.destination
  BottomNavigation {
    screens.forEach { screen ->
      AddItem(
        screen = screen,
        currentDestination = currentDestination,
        navController = navController)
    }
  }
}

@Composable
fun RowScope.AddItem(
  screen: AppScreens,
  currentDestination: NavDestination?,
  navController: NavHostController
) {
  val selected = currentDestination?.hierarchy?.any {
    it.route == screen.route
  } == true

  val currentScreen = navController.currentDestination?.route

  BottomNavigationItem(
    modifier = Modifier
      .background(color = Color(0xFFFFFFFF)),
    icon = {
      AsyncImage(
        model = screen.icon,
        contentDescription = "Navigation Icon",
        modifier = Modifier
          .fillMaxSize(0.55f)
          .graphicsLayer { alpha = if (currentScreen != screen.route) 0.35f else 1f })
           },
    selected = selected,
    onClick = { if(currentScreen != screen.route) navController.navigate(screen.route) },
  )
}