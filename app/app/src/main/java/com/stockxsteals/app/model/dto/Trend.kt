package com.stockxsteals.app.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trend(@SerialName("id") val id: String,
                 @SerialName("slug") val slug: String,
                 @SerialName("name") val name: String,
                 @SerialName("sku") val sku: String,
                 @SerialName("brand") val brand: String,
                 @SerialName("image") val image: String,
                 @SerialName("category") val category: String,
                 @SerialName("release_date") val release_date: String)


fun blankTrend(): Trend {
  return Trend(
    "",
    "",
    "",
    "",
    "",
    "",
    "",
    ""
  )
}
