package com.stockxsteals.app.model.ui

sealed class PremiumSellingPoint (
    val icon: String,
    val title: String,
    val description: String
) {

  object Searches : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=132&format=png",
    title =  "Unlimited Searches",
    description = "The base app only allows you to make only 2 L8test. searches per day. With Lt8est+, on the other hand, " +
                  "you will be able to make an unlimited amount of searches every day."
  )

  object Features : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=25224&format=png",
    title =  "New Features",
    description = "As part of your L8test+ subscription, you will have access the all new features for as long as your " +
                  "subscribed at no extra cost."
  )

  object Community : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=3685&format=png",
    title =  "Unlocking Community Access",
    description = "At L8test. We value the asset that presents itself with a loyal, active social media presence. " +
                  "and we care about the users who invest in the platform and its longevity. (continue scrolling)" +
                  "\n\n\n" +
                  "With L8test+, you will have access to all our social media platforms, including Discord, where you" +
                  "can have direct access to our team if you encounter issues on the app." +
                  "\n\n\n" +
                  "You will also be the first to find out about new features and UI updates as well as progressions and involvement in our research and development. \n\n\n" +
                  "At L8test. we value a quality following above all and we firmly understand that building a solid " +
                  "will ensure scalable and measurable growth in this platform and its offerings."
  )

}

fun sellingPointsList(): List<PremiumSellingPoint> {
  return listOf(
    PremiumSellingPoint.Searches,
    PremiumSellingPoint.Features,
    PremiumSellingPoint.Community
  )
}
