package com.stockxsteals.app.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(@SerialName("id") val id: String,
                   @SerialName("slug") val slug: String,
                   @SerialName("name") val name: String,
                   @SerialName("brand") val brand: String,
                   @SerialName("image") val image: String,
                   @SerialName("description") var description: String,
                   @SerialName("category") val category: String,
                   @SerialName("model") val model: String,
                   @SerialName("sku") val sku: String,
                   @SerialName("traits") val traits: List<Traits>,
                   @SerialName("variants") val variants: List<Variants>,
                   @SerialName("market") val market: Market
)

fun blankProduct(): Product {
  return Product("","","", "",
    "" , "", "", "",
    "",
    listOf(), listOf(), Market(
      Bids(0,0,0,0),
      Sales(0,0,0f,0f, 0, 0, 0f))
  )
}