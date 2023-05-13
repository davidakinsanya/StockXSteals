package com.stockxsteals.app.datasource.impl

import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.DailySearchDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DailySearchImpl(db : Database): DailySearchDataSource {
  private val queries = db.dailysearchquotaQueries

  override suspend fun getSearchNumber(): Int {
   return withContext(Dispatchers.IO) {
     queries.getSearchNumber().executeAsOne().toInt()
   }
  }

  override suspend fun getSearchLimit(): Int {
    return withContext(Dispatchers.IO) {
      queries.getSearchLimit().executeAsOne().toInt()
    }
  }

  override suspend fun getTimeStamp(): String {
    return withContext(Dispatchers.IO) {
      queries.getTimeStamp().executeAsOne()
    }
  }

  override suspend fun insertSearch(timestamp: String, search_limit: Int, search_number: Int) {
    withContext(Dispatchers.IO) {
      queries.insertSearch(timestamp, search_limit.toLong(), search_number.toLong())
    }
  }

  override suspend fun addTimeStamp(timestamp: String) {
    withContext(Dispatchers.IO) {
      queries.updateTimeStamp(timestamp)
    }
  }
}