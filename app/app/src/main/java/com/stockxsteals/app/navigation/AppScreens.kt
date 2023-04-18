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

  object SearchByCode: AppScreens (
    route = "search_by_code",
    title = "Search By Code",
    icon = "https://img.icons8.com/color/512/barcode-scanner.png"
  )

  object SearchBySlug: AppScreens(
    route = "search_by_slug",
    title = "Search By Slug",
    icon = "https://img.icons8.com/pulsar-color/512/new-product.png"
  )

  object TopSearch: AppScreens(
    route = "top_search",
    title = "Search",
    icon = ""
  )
}
