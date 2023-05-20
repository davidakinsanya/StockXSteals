package com.stockxsteals.app.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Product(@SerializedName("id") @Expose val id: String,
                   @SerializedName("slug") @Expose val slug: String,
                   @SerializedName("name") @Expose val name: String,
                   @SerializedName("brand") @Expose val brand: String,
                   @SerializedName("image") @Expose val image: String,
                   @SerializedName("description") @Expose var description: String,
                   @SerializedName("category") @Expose val category: String,
                   @SerializedName("model") @Expose val model: String,
                   @SerializedName("sku") @Expose val sku: String,
                   @SerializedName("traits") @Expose val traits: List<Traits>,
                   @SerializedName("variants") @Expose val variants: List<Variants>,
                   @SerializedName("market") @Expose val market: Market
)

fun blankProduct(): Product {
  return Product("","","",
    "" , "", "",
    "", "", "",
    listOf(), listOf(), Market(
      Bids(0,0,0,0),
      Sales(0,0,0f,0f, 0, 0, 0f), )
  )
}