package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.DailySearchHistorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import db.entity.DailySearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DailySearchHistoryViewModel
@Inject constructor (
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

   suspend fun addSearch(timestamp: String,
                                 name: String,
                                 image: String,
                                 json: String) {

    withContext(Dispatchers.IO) {
      dailySearchHistorySource.addSearch(
        timestamp,
        name,
        image,
        json)
    }
  }

   private suspend fun clearSearches() {
    withContext(Dispatchers.IO) {
      dailySearchHistorySource.clearSearches()
    }
  }
}