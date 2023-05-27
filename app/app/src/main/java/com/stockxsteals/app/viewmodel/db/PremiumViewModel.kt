package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.PremiumDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PremiumViewModel (
  private val premiumDataSource: PremiumDataSource
): ViewModel() {

   suspend fun getIsPremium(): Int {
    return withContext(Dispatchers.IO) {
      premiumDataSource.getIsPremium()
    }
  }

   suspend fun setIsPremium(isPremium: Int) {
    withContext(Dispatchers.IO) {
      premiumDataSource.setIsPremium(isPremium)
    }
  }
}