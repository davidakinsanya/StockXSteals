package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.DailySearchDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DailySearchViewModel
@Inject constructor (
  private val dailySearchDataSource: DailySearchDataSource
  ): ViewModel() {

  suspend fun getSearchNumber(): Int {
    return withContext(Dispatchers.IO) {
      dailySearchDataSource.getSearchNumber()
    }
  }

  suspend fun getSearchLimit(): Int {
    return withContext(Dispatchers.IO) {
      dailySearchDataSource.getSearchLimit()
    }
  }

  suspend fun getTimeStamp(): String {
    return withContext(Dispatchers.IO) {
      dailySearchDataSource.getTimeStamp()
    }
  }

  suspend fun insertSearch(timestamp: String, search_limit: Int, search_number: Int) {
    withContext(Dispatchers.IO) {
      dailySearchDataSource.insertSearch(timestamp, search_limit, search_number)
    }
  }

  suspend fun addTimeStamp(timestamp: String) {
    withContext(Dispatchers.IO) {
      dailySearchDataSource.addTimeStamp(timestamp)
    }
  }
}