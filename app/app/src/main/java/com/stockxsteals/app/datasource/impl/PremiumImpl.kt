package com.stockxsteals.app.datasource.impl

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.PremiumDataSource
import db.entity.Premium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PremiumImpl(db: Database): PremiumDataSource {
  private val queries = db.premiumQueries

  override fun getPremiumQuotas(): Flow<List<Premium>> {
    return queries.getPremiumQuota().asFlow().mapToList()
  }

  override suspend fun newPremiumQuota() {
    withContext(Dispatchers.IO) {
      queries.newPremiumQuota()
    }
  }

  override suspend fun getIsPremium(id: Long): Int {
    return withContext(Dispatchers.IO) {
      queries.getIsPremium(id).executeAsOne().toInt()
    }
  }

  override suspend fun setIsPremium(isPremium: Int) {
    withContext(Dispatchers.IO) {
      queries.setIsPremium(isPremium.toLong())
    }
  }
}