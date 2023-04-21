package com.stockxsteals.app.model

data class SearchWithFilters(var code: String,
                             var slug: String,
                             var country: String,
                             var currency: String,
                             var sizeType: String,
                             var size: Double)
