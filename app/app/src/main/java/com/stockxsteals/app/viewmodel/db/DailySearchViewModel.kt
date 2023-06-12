package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.DailySearchDataSource
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.utils.sameDateCheck
import db.entity.DailySearchQuota
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DailySearchViewModel (
  private val dailySearchDataSource: DailySearchDataSource
  ): ViewModel() {

  val quota = getQuotaList()

  suspend fun insertSearch() {
    withContext(Dispatchers.IO) {
      dailySearchDataSource.insertSearch(getCurrentDate(), 1)
    }
  }

  private fun getQuotaList(): Flow<List<DailySearchQuota>> {
    return dailySearchDataSource.getQuota()

  }

  suspend fun dbLogic(quota: DailySearchQuota): Int {
    var result: Int

    withContext(Dispatchers.IO) {
      if (sameDateCheck(quota.timestamp)) {
        val newSearchNumber = if (quota.search_limit > quota.search_number)
            dailySearchDataSource.getSearchNumber(quota.id) + 1
          else null

        result = if (newSearchNumber != null) {
          dailySearchDataSource.updateSearchNumber(newSearchNumber, quota.id)
          1
        } else -1

      } else {
        dailySearchDataSource.updateSearchNumber(1, quota.id)
        dailySearchDataSource.updateTimeStamp(getCurrentDate(), quota.id)
        println(quota.timestamp)
        result = 1
      }
    }
    return result
  }
}