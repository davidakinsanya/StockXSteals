package com.stockxsteals.app.datasource.impl

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.DailySearchDataSource
import db.entity.DailySearchQuota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DailySearchImpl(db : Database): DailySearchDataSource {
  private val queries = db.dailySearchQuotaQueries

  override suspend fun getSearchNumber(id: Long): Int {
   return withContext(Dispatchers.IO) {
     queries.getSearchNumber(id).executeAsOne().toInt()
   }
  }

  override suspend fun getSearchLimit(id: Long): Int {
    return withContext(Dispatchers.IO) {
      queries.getSearchLimit(id).executeAsOne().toInt()
    }
  }

  override suspend fun getTimeStamp(id: Long): String {
    return withContext(Dispatchers.IO) {
      queries.getTimeStamp(id).executeAsOne()
    }
  }

  override suspend fun insertSearch(timestamp: String, search_limit: Int, search_number: Int) {
    withContext(Dispatchers.IO) {
      queries.insertSearch(timestamp, search_limit.toLong(), search_number.toLong())
    }
  }

  override suspend fun updateSearchNumber(newNumber: Int, id: Long) {
    withContext(Dispatchers.IO) {
      queries.updateSearchNumber(newNumber.toLong(), id)
    }
  }

  override suspend fun updateSearchLimit(newNumber: Int, id: Long) {
    withContext(Dispatchers.IO) {
      queries.updateSearchLimit(newNumber.toLong(), id)
    }
  }

  override suspend fun getQuota(): Flow<List<DailySearchQuota>> {
    return withContext(Dispatchers.IO) {
      queries.getQuota().asFlow().mapToList()
    }
  }

  override suspend fun updateTimeStamp(timestamp: String, id: Long) {
    withContext(Dispatchers.IO) {
      queries.updateTimeStamp(timestamp, id)
    }
  }
}