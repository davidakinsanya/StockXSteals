package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.datasource.intrface.DailySearchDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import db.entity.DailySearchQuota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DailySearchViewModel
@Inject constructor (
  private val dailySearchDataSource: DailySearchDataSource
  ): ViewModel() {

  lateinit var quota: Flow<List<DailySearchQuota>>

  init {
    viewModelScope.launch {
      quota = getQuota()
    }
  }

  suspend fun insertSearch(timestamp: String, search_limit: Int, search_number: Int) {
    withContext(Dispatchers.IO) {
      dailySearchDataSource.insertSearch(timestamp, search_limit, search_number)
    }
  }

  private suspend fun getQuota(): Flow<List<DailySearchQuota>> {
    return withContext(Dispatchers.IO) {
      dailySearchDataSource.getQuota()
    }
  }

  suspend fun dbLogic(quota: DailySearchQuota): Int {
    withContext(Dispatchers.IO) {
      if (sameDateCheck(quota.timestamp)) {
        val newSearchNumber = if (quota.search_limit <= quota.search_number)
          dailySearchDataSource.getSearchNumber(quota.id) + 1
          else null

          if (newSearchNumber != null) {
            dailySearchDataSource.updateSearchNumber(newSearchNumber, quota.id)
            return@withContext 1
          } else return@withContext -1

      } else {
        dailySearchDataSource.updateSearchNumber(1, quota.id)
        dailySearchDataSource.updateTimeStamp(LocalDateTime.now().toString(), quota.id)
        return@withContext 1
      }
    }
    return -1
  }

  private fun sameDateCheck(timestamp: String): Boolean {
    val stampDate = timestamp.split("T")
    val currentDate = LocalDateTime.now().toString().split("T")
    return currentDate.toString().equals(stampDate)
  }
}