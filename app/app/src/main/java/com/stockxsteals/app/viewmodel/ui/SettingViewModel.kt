package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import db.entity.Premium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingViewModel(private val qonversionModel: QonversionViewModel,
                       private val premiumModel: PremiumViewModel,
                       private val historyModel: DailySearchHistoryViewModel): ViewModel() {


  fun getQonversionModel(): QonversionViewModel {
    return qonversionModel
  }

  fun getPremiumModel(): PremiumViewModel {
    return premiumModel
  }

  fun getHistoryModel(): DailySearchHistoryViewModel {
    return historyModel
  }

  suspend fun isPremium(premium: List<Premium>): Boolean {
    withContext(Dispatchers.IO) {
      if (premium.isEmpty()) {
        getPremiumModel().newPremiumQuota()
        return@withContext false
      }
      return@withContext getPremiumModel().getIsPremium(premium[0].id) == 1
    }
    return false
  }
}