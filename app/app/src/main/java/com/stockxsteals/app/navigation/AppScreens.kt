package com.stockxsteals.app.navigation

sealed class AppScreens (
  val route: String,
  val title: String,
  val icon: String
) {
  object Trends : AppScreens(
    route = "trends",
    title = "Trends",
    icon =  "https://img.icons8.com/offices/512/graph.png"
  )

  object Search: AppScreens (
    route = "search",
    title = "Search",
    icon = "https://img.icons8.com/material/512/search-more.png"
  )

  object Settings: AppScreens(
    route = "settings",
    title = "Settings",
    icon = "https://img.icons8.com/metro/512/toggle-on.png"
  )

  object TopSearch: AppScreens(
    route = "top_search",
    title = "Search",
    icon = ""
  )
}
