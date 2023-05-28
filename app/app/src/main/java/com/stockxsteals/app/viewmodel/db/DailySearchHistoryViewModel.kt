package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.DailySearchHistorySource
import db.entity.DailySearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DailySearchHistoryViewModel (
  private val dailySearchHistorySource: DailySearchHistorySource
  ): ViewModel() {

  val searches = getSearchHistory()

  private fun getSearchHistory(): Flow<List<DailySearchHistory>> {
    return dailySearchHistorySource.getSearchHistory()

  }

   suspend fun deleteSearch(id: Long) {
    withContext(Dispatchers.IO) {
      dailySearchHistorySource.deleteSearch(id)
    }
  }

  private suspend fun clearSearches() {
    withContext(Dispatchers.IO) {
      dailySearchHistorySource.clearSearches()
    }
  }

   suspend fun addSearch(timestamp: String,
                         country: String,
                         currency: String,
                         sizeType: String,
                         size: Double,
                         name: String,
                         image: String,
                         json: String) {

    withContext(Dispatchers.IO) {
      dailySearchHistorySource.addSearch(
        timestamp,
        country,
        currency,
        sizeType,
        size,
        name,
        image,
        json)
    }
  }

  suspend fun updateSearch(timestamp: String,
                        country: String,
                        currency: String,
                        sizeType: String,
                        size: Double,
                        name: String,
                        image: String,
                        json: String,
                        id: Long) {

    withContext(Dispatchers.IO) {
      dailySearchHistorySource.updateSearch(
        timestamp,
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

  suspend fun getSearchByStamp(stamp: String): DailySearchHistory {
    return withContext(Dispatchers.IO) {
      dailySearchHistorySource.getSearchByStamp(stamp)
    }
  }
}