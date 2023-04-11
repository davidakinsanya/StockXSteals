package com.stockxsteals.server.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Traits(@SerializedName("name") @Expose val name: String,
                  @SerializedName("value") @Expose val value: String)


data class Variants(@SerializedName("id") @Expose val id: String,
                    @SerializedName("sizes") @Expose val sizes: List<Sizes>,
                    @SerializedName("gtins") @Expose val gtins: List<Gtins>,
                    @SerializedName("market") @Expose val market: Market)

data class Sizes(@SerializedName("size") @Expose val size: String,
                @SerializedName("type") @Expose val type: String)

data class Gtins(@SerializedName("type") @Expose val type: String,
                @SerializedName("identifier") @Expose val identifier: String)

data class Market(@SerializedName("bids") @Expose val bids: Bids,
                  @SerializedName("sales") @Expose val sales: Sales)

data class Bids(@SerializedName("lowest_ask") @Expose val lowest_ask: Int,
                @SerializedName("highest_bid") @Expose val highest_bid: Int,
                @SerializedName("number_asks") @Expose val num_asks: Int,
                @SerializedName("number_bids") @Expose val num_bids: Int)

data class Sales(@SerializedName("annual_high") @Expose val annual_high: Int,
                 @SerializedName("annual_low") @Expose val annual_low: Int,
                 @SerializedName("volatility") @Expose val volatility: Float,
                 @SerializedName("price_premium") @Expose val price_premium: Float,
                 @SerializedName("last_sale") @Expose val last_sale: Int,
                 @SerializedName("change_value") @Expose val change_value: Int,
                 @SerializedName("change_percentage") @Expose val change_percent: Float)

