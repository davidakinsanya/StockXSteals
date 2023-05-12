package com.stockxsteals.app.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("daily_search")
data class DailySearch(@PrimaryKey
                       val timestamp: String,
                       val search_limit: Int,
                       val search_number: Int)
