package com.stockxsteals.app.model.ui

sealed class SocialMedia (
  val socials: String,
  val icon: String,
  val link: String,
) {
  object Instagram : SocialMedia (
    socials = "Instagram",
    icon = "https://img.icons8.com/?size=512&id=32292&format=png",
    link = "https://www.instagram.com/l8test.app/"
  )

  object Discord : SocialMedia (
    socials = "Discord",
    icon = "https://img.icons8.com/?size=512&id=gxdxl0oMFoSA&format=png",
    link = "https://discord.gg/ErqGVceQE6"
  )
}

fun allSocials(): List<SocialMedia> {
  return listOf(
    SocialMedia.Instagram,
    SocialMedia.Discord
  )
}
