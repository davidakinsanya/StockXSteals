package com.stockxsteals.app.datasource.intrface

import db.entity.DailySearchHistory
import kotlinx.coroutines.flow.Flow

interface DailySearchHistorySource {

  fun getSearchHistory(): Flow<List<DailySearchHistory>>

  suspend fun deleteSearch(id: Long)

  suspend fun addSearch(timestamp: String,
                        name: String,
                        image: String,
                        json: String)

  suspend fun clearSearches()
}