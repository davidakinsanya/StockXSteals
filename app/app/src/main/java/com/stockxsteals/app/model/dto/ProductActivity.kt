package com.stockxsteals.app.model.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProductActivity(@SerializedName("amount") @Expose val amount: Int,
                           @SerializedName("count") @Expose val count: Int?,
                           @SerializedName("size") @Expose val size: String)

@Serializable
data class ProductActivityData(@SerializedName("bids") @Expose val bids: List<ProductActivity>,
                               @SerializedName("asks") @Expose val asks: List<ProductActivity>,
                               @SerializedName("sales") @Expose val sales: List<ProductActivity>)