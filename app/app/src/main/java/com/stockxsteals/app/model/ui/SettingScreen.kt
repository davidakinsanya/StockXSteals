package com.stockxsteals.app.model.ui

sealed class SettingScreen(
  val screen: String,
  val icon: String,
) {
  object Upgrade: SettingScreen (
    screen = "Upgrade",
    icon = "https://img.icons8.com/?size=512&id=RsreCOAj5rfI&format=png",
  )

  object Searches: SettingScreen (
    screen = "Searches",
    icon = "https://img.icons8.com/?size=512&id=131&format=png"
  )

  object AboutUs: SettingScreen (
    screen = "About Us",
    icon = "https://img.icons8.com/?size=512&id=89239&format=png"
  )

  object TC: SettingScreen (
    screen = "T&Cs",
    icon = "https://img.icons8.com/?size=512&id=102445&format=png"
  )

  object LogOut: SettingScreen (
    screen = "Log Out",
    icon = "https://img.icons8.com/?size=512&id=2444&format=png"
  )

  object Socials: SettingScreen (
    screen = "Socials",
    icon = "https://img.icons8.com/?size=512&id=7411&format=png"
  )
}

fun settingScreensList(): MutableList<SettingScreen> {
  return mutableListOf(
    SettingScreen.Upgrade,
    SettingScreen.Searches,
    SettingScreen.AboutUs,
    SettingScreen.TC,
    SettingScreen.LogOut
  )
}
