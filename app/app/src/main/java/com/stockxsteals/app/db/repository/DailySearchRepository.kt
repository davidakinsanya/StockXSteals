package com.stockxsteals.app.db.repository

import androidx.lifecycle.LiveData
import com.stockxsteals.app.db.dao.DailySearchDAO


class DailySearchRepository(private val dailySearchDAO: DailySearchDAO) {
  val readSearchNumber: LiveData<Int> = dailySearchDAO.getSearchNumber()

  suspend fun appendSearch(newSearch: Int) {
    dailySearchDAO.appendSearch(newSearch)
  }
}