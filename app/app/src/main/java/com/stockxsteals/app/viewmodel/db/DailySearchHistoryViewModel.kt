package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.datasource.intrface.DailySearchHistorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import db.entity.DailySearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DailySearchHistoryViewModel
@Inject constructor (
  private val dailySearchHistorySource: DailySearchHistorySource
  ): ViewModel() {

  lateinit var searches: Flow<List<DailySearchHistory>>

  init {
    viewModelScope.launch {
      searches = getSearchHistory()
    }
  }
  private suspend fun getSearchHistory(): Flow<List<DailySearchHistory>> {
    return withContext(Dispatchers.IO) {
      dailySearchHistorySource.getSearchHistory()
    }
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

   suspend fun clearSearches() {
    withContext(Dispatchers.IO) {
      dailySearchHistorySource.clearSearches()
    }
  }
}