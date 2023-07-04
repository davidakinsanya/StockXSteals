package com.stockxsteals.app.model.ui

sealed class Socials (
  val socials: String,
  val icon: String,
  val link: String,
) {
  object Instagram : Socials (
    socials = "Instagram",
    icon = "https://img.icons8.com/?size=512&id=32292&format=png",
    link = "https://www.instagram.com/l8test.app/"
  )

  object Discord : Socials (
    socials = "Discord",
    icon = "https://img.icons8.com/?size=512&id=gxdxl0oMFoSA&format=png",
    link = "https://www.google.co.uk/"
  )
}

fun allSocials(): List<Socials> {
  return listOf(
    Socials.Instagram,
    Socials.Discord
  )
}
