package com.stockxsteals.app.datasource.intrface


interface DailySearchDataSource {

  suspend fun getSearchNumber(): Int

  suspend fun getSearchLimit(): Int

  suspend fun getTimeStamp(): String

  suspend fun insertSearch(timestamp: String, search_limit: Int, search_number: Int)

  suspend fun addTimeStamp(timestamp: String)

}