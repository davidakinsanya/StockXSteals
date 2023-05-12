package com.stockxsteals.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filter_presets")
data class FilterPreset(
                         @PrimaryKey(autoGenerate = true)
                         val id: Int,
                         val country: String,
                         val currency: String,
                         val sizeType: String,
                         val size: Double)