package com.stockxsteals.app.datasource.intrface

import db.entity.Premium
import kotlinx.coroutines.flow.Flow


interface PremiumDataSource {

  fun getPremiumQuotas(): Flow<List<Premium>>

  suspend fun newPremiumQuota()

  suspend fun getIsPremium(id: Long): Int

  suspend fun setIsPremium(isPremium: Int)

  suspend fun deleteRows(id: Long)
}