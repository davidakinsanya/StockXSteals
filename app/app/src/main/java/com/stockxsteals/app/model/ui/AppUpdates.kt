package com.stockxsteals.app.model.ui

sealed class AppUpdates(
  val date: String,
  val title: String,
  val description: String
) {
  object VERSION_1_0 : AppUpdates (
    date = "03/07",
    title = "Finalising L8test 1.0",
    description = "99% of the first version of the app is complete. " +
            "It can be expected for the first app release to occur before the " +
            "month ends on just the Google Play Store.",
  )
}

fun allUpdates(): List<AppUpdates> {
  return listOf(
    AppUpdates.VERSION_1_0
  )
}
