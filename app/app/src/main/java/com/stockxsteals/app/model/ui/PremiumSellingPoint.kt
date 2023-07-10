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
    description = "Pour into us, so we can simply pour back into the app. " +
                  "We want to continue working on new sneaker-centric features, " +
                  "steering us clear from the next big thing. " +
                  "As part of your L8test+ subscription, you will have access " +
                  "all new features for as long as you are subscribed at no extra cost."
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

  object Cancel : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=3062&format=png",
    title = "Cancel Anytime",
    description = "You will not be contractually obligated to continue paying for our premium service and " +
                  "you can unsubscribe and resubscribe to L8test+ if or when you please."
  )

  object Ads : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=40463&format=png",
    title = "No Ads, Ever!",
    description = "L8test. is reliant on subscription revenue in order to continually push the best " +
                  "possible product for the our market of sneaker lovers. We strongly believe ads will gravely hinder " +
                  "our ability to do so."
  )

  object Performance : PremiumSellingPoint (
    icon = "https://img.icons8.com/?size=512&id=41152&format=png",
    title = "Steady Performance Increases",
    description = "We understand the app is at it's beginning stages. At L8test, we are not the type to dump an " +
                  "app on the market and leave it there knowing it is not the best it can be. Our early user will " +
                  "benefit most through seeing gradual increases in our performance and ability to deliver our service " +
                  "over time."
  )
}

fun sellingPointsList(): List<PremiumSellingPoint> {
  return listOf(
    PremiumSellingPoint.Searches,
    PremiumSellingPoint.Features,
    PremiumSellingPoint.Community,
    PremiumSellingPoint.Cancel,
    PremiumSellingPoint.Ads,
    PremiumSellingPoint.Performance
  )
}
