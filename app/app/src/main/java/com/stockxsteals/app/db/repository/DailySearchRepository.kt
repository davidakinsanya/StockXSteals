package com.stockxsteals.app.db.repository

import androidx.lifecycle.LiveData
import com.stockxsteals.app.db.dao.DailySearchDAO


class DailySearchRepository(private val dailySearchDAO: DailySearchDAO) {
  val readSearchNumber: LiveData<Int> = dailySearchDAO.getSearchNumber()
  val readSearchLimit: LiveData<Int> = dailySearchDAO.getSearchLimit()
  val readTimeStamp: LiveData<String> = dailySearchDAO.getTimeStamp()

  suspend fun appendSearch(newSearch: Int) {
    dailySearchDAO.appendSearch(newSearch)
  }

  suspend fun addSearchLimit(limit: Int) {
    dailySearchDAO.appendSearchLimit(limit)
  }

  suspend fun addTimeStamp(timestamp: String) {
    dailySearchDAO.appendTimeStamp(timestamp)
  }
}