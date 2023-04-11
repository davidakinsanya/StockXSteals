package com.stockxsteals.server.dto.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Trend(@SerializedName("id") @Expose val id: String,
                 @SerializedName("slug") @Expose val slug: String,
                 @SerializedName("name") @Expose val name: String,
                 @SerializedName("sku") @Expose val sku: String,
                 @SerializedName("brand") @Expose val brand: String,
                 @SerializedName("image") @Expose val image: String,
                 @SerializedName("category") @Expose val category: String,
                 @SerializedName("release_date") @Expose val release_date: String)
