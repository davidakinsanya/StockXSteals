package com.stockxsteals.app.db.entity


data class FilterPreset( val id: Int,
                         val country: String,
                         val currency: String,
                         val sizeType: String,
                         val size: Double)