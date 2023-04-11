package com.stockxsteals.server.dto.ui


data class Sneaker(val name: String, 
                   val sku: String,
                   val slug: String,
                   val desc: String, 
                   val last_sale: Int?,
                   val predicted_price: Float?,
                   val availability: Availability?,
                   val availability_diff: Int?,
                   var size_percent: Map<String, Double>?,
                   var common_sizes: Map<String, List<String>>?)
