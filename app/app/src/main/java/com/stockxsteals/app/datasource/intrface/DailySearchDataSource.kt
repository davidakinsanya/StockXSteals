package com.stockxsteals.app.datasource.intrface

import db.entity.DailySearchQuota
import kotlinx.coroutines.flow.Flow

interface DailySearchDataSource {

  suspend fun getSearchNumber(id: Long): Int

  suspend fun getSearchLimit(id: Long): Int

  suspend fun getTimeStamp(id: Long): String

  suspend fun insertSearch(timestamp: String, search_number: Int)

  suspend fun updateSearchNumber(newNumber: Int, id: Long)

  suspend fun updateSearchLimit(newNumber: Int, id: Long)

  fun getQuota(): Flow<List<DailySearchQuota>>

  suspend fun updateTimeStamp(timestamp: String, id: Long)

}