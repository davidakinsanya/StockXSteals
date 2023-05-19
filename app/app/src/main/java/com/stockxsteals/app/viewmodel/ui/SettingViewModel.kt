package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel

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
}