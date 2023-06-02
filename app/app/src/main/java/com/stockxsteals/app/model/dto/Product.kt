package com.stockxsteals.app.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(@SerialName("id") val id: String,
                   @SerialName("slug") val slug: String,
                   @SerialName("name") val name: String,
                   @SerialName("brand") val brand: String,
                   @SerialName("image") val image: String,
                   @SerialName("colorway") val colorway: String,
                   @SerialName("description") var description: String = "",
                   @SerialName("category") var category: String = "",
                   @SerialName("model") var model: String = "",
                   @SerialName("sku") val sku: String,
                   @SerialName("traits") var traits: List<Traits> = listOf(),
                   @SerialName("variants") var variants: List<Variants> = listOf(),


                   @SerialName("market") var market: Market = Market(
                     Bids(0,0,0,0),
                     Sales(0,0,0f,0f, 0, 0, 0f)),
)

fun blankProduct(): Product {
  return Product("","","", "",
    "" , "", "", "",
    "", "",
    listOf(), listOf(), Market(
      Bids(0,0,0,0),
      Sales(0,0,0f,0f, 0, 0, 0f))
  )
}