package com.stockxsteals.app.datasource.impl

import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.PremiumDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PremiumImpl(db: Database): PremiumDataSource {
  private val queries = db.premiumQueries

  override suspend fun getIsPremium(): Int {
    return withContext(Dispatchers.IO) {
      queries.getIsPremium().executeAsOne().toInt()
    }
  }

  override suspend fun setIsPremium(isPremium: Int) {
    withContext(Dispatchers.IO) {
      queries.setIsPremium(isPremium.toLong())
    }
  }
}