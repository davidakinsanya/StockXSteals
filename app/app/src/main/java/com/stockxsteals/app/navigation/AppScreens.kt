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
    icon = "https://img.icons8.com/color/512/search.png"
  )

  object Settings: AppScreens(
    route = "settings",
    title = "Settings",
    icon = "https://img.icons8.com/ios-glyphs/512/settings.png"
  )

  object SettingScreen: AppScreens(
    route = "setting_screen",
    title = "Setting Screen",
    icon = ""
  )

  object TopSearch: AppScreens(
    route = "top_search",
    title = "Search",
    icon = ""
  )

  object SneakerSearch: AppScreens(
    route = "sneaker_search",
    title = "Sneaker Search",
    icon = ""
  )
}
