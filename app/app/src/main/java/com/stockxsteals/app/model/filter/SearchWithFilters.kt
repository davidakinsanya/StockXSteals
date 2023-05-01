package com.stockxsteals.app.model.filter

data class SearchWithFilters(var slug: String,
                             var country: String,
                             var currency: String,
                             var sizeType: String,
                             var size: Double)
