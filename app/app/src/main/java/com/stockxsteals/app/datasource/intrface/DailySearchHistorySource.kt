package com.stockxsteals.app.datasource.intrface

import db.entity.DailySearchHistory
import kotlinx.coroutines.flow.Flow

interface DailySearchHistorySource {

  fun getSearchHistory(): Flow<List<DailySearchHistory>>

  suspend fun deleteSearch(stamp: String)

  suspend fun addSearch(timestamp: String,
                        country: String,
                        currency: String,
                        sizeType: String,
                        size: Double,
                        name: String,
                        image: String,
                        json: String)

  suspend fun updateSearch(timestamp: String,
                           country: String,
                           currency: String,
                           sizeType: String,
                           size: Double,
                           name: String,
                           image: String,
                           json: String,
                           id: Long)

  suspend fun getSearchByStamp(stamp: String): DailySearchHistory

  suspend fun clearSearches()
}