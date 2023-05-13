package com.stockxsteals.app.db.database

import com.stockxsteals.app.db.dao.DailySearchDAO

abstract class DailySearchDatabase() {
  abstract fun dailySearchDAO(): DailySearchDAO

  companion object {
  }

}