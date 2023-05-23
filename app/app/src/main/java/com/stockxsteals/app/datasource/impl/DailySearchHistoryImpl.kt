package com.stockxsteals.app.datasource.impl

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.DailySearchHistorySource
import db.entity.DailySearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DailySearchHistoryImpl(db: Database): DailySearchHistorySource {
  private val queries = db.dailySearchHistoryQueries

  override suspend fun getSearchHistory(): Flow<List<DailySearchHistory>> {
   return withContext(Dispatchers.IO) {
     queries.getSearchHistory().asFlow().mapToList()
   }
  }

  override suspend fun deleteSearch(id: Long) {
   return withContext(Dispatchers.IO) {
     queries.deleteSearch(id)
   }
  }

  override suspend fun addSearch(timestamp: String, name: String, image: String, json: String) {
    withContext(Dispatchers.IO) {
      queries.addSearch(timestamp, name, image, json)
    }
  }

  override suspend fun clearSearches() {
   withContext(Dispatchers.IO) {
     queries.clearSearches()
   }
  }
}