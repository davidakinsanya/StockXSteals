package com.stockxsteals.server.dto.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.stockxsteals.server.dto.Market
import com.stockxsteals.server.dto.Traits
import com.stockxsteals.server.dto.Variants

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
