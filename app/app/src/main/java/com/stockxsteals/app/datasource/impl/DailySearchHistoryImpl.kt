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

  override fun getSearchHistory(): Flow<List<DailySearchHistory>> {
   return queries.getSearchHistory().asFlow().mapToList()
  }

  override suspend fun deleteSearch(id: Long) {
   return withContext(Dispatchers.IO) {
     queries.deleteSearch(id)
   }
  }

  override suspend fun addSearch(
    timestamp: String,
    country: String,
    currency: String,
    sizeType: String,
    size: Double,
    name: String,
    image: String,
    json: String
  ) {
    queries.addSearch(timestamp,
      country,
      currency,
      sizeType,
      size,
      name,
      image,
      json)
  }

  override suspend fun updateSearch(
    timestamp: String,
    country: String,
    currency: String,
    sizeType: String,
    size: Double,
    name: String,
    image: String,
    json: String,
    id: Long
  ) {
    withContext(Dispatchers.IO) {
      queries.updateSearch(timestamp,
        country,
        currency,
        sizeType,
        size,
        name,
        image,
        json,
        id)
    }
  }

  override suspend fun getSearchByStamp(stamp: String): DailySearchHistory {
    return withContext(Dispatchers.IO) {
      queries.getSearchByStamp(stamp).executeAsOne()
    }
  }

  override suspend fun clearSearches() {
   withContext(Dispatchers.IO) {
     queries.clearSearches()
   }
  }
}