package com.stockxsteals.app.model.ui

sealed class SettingScreens (
  val screen: String,
  val icon: String,
) {
  object Upgrade: SettingScreens (
    screen = "Upgrade",
    icon = "https://img.icons8.com/?size=512&id=RsreCOAj5rfI&format=png",
  )

  object Searches: SettingScreens (
    screen = "Searches",
    icon = "https://img.icons8.com/?size=512&id=131&format=png"
  )

  object AboutUs: SettingScreens (
    screen = "About Us",
    icon = "https://img.icons8.com/?size=512&id=89239&format=png"
  )

  object TC: SettingScreens (
    screen = "T&Cs",
    icon = "https://img.icons8.com/?size=512&id=102445&format=png"
  )

  object Socials: SettingScreens (
    screen = "Socials",
    icon = "https://img.icons8.com/?size=512&id=7411&format=png"
  )
}

fun settingScreensList(): MutableList<SettingScreens> {
  return mutableListOf(
    SettingScreens.Upgrade,
    SettingScreens.Searches,
    SettingScreens.AboutUs,
    SettingScreens.TC,
  )
}
