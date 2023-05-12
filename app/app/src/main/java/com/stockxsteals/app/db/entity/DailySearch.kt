package com.stockxsteals.app.db.entity

import androidx.room.Entity


@Entity("daily_search")
data class DailySearch( val search_limit: Int,
                        val search_number: Int)
