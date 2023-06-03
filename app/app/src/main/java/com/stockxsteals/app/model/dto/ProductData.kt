package com.stockxsteals.app.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Variants(@SerialName("id") val id: String,
                    @SerialName("sizes") val sizes: List<Sizes>,
                    @SerialName("gtins") val gtins: List<Gtins>,
                    @SerialName("traits") val traits: Traits2,
                    @SerialName("market") val market: Market)

@Serializable
data class Sizes(@SerialName("size") val size: String,
                 @SerialName("type") val type: String)

@Serializable
data class Traits(@SerialName("name") val name: String,
                  @SerialName("value") val value: String)

@Serializable
data class Traits2(@SerialName("size") val size: String)

@Serializable
data class Gtins(@SerialName("type") val type: String,
                 @SerialName("identifier") val identifier: String)

@Serializable
data class Market(@SerialName("bids") val bids: Bids,
                  @SerialName("sales") val sales: Sales2)

@Serializable
data class Bids(@SerialName("lowest_ask") val lowest_ask: Int?,
                @SerialName("highest_bid") val highest_bid: Int?,
                @SerialName("number_asks") val num_asks: Int?,
                @SerialName("number_bids") val num_bids: Int?)

@Serializable
data class Sales(@SerialName("annual_high") val annual_high: Int?,
                 @SerialName("annual_low") val annual_low: Int?,
                 @SerialName("volatility") val volatility: Float?,
                 @SerialName("price_premium") val price_premium: Float?,
                 @SerialName("last_sale") val last_sale: Int?,
                 @SerialName("change_value") val change_value: Int?,
                 @SerialName("change_percentage") val change_percent: Float?)

@Serializable
data class Sales2(@SerialName("last_sale") val last_sale: Int,
                  @SerialName("sales_last_72h") val last_sale_72h: Int)