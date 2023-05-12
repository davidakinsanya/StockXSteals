package com.stockxsteals.app.db.repository

import androidx.lifecycle.LiveData
import com.stockxsteals.app.db.dao.PremiumDAO

class PremiumRepository(private val premiumDAO: PremiumDAO) {
  val readIsPremium: LiveData<Boolean> = premiumDAO.getIsPremium()

  suspend fun setPremium(isPremium: Boolean) {
    premiumDAO.setPremium(isPremium)
  }
}