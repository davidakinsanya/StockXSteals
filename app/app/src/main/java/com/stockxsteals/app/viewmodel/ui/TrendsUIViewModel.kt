package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.utils.fileIsOld
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrendsUIViewModel(private val networkModel: NetworkViewModel,
                        private val historyModel: DailySearchHistoryViewModel,
                        private val dailySearchModel: DailySearchViewModel,
                        private val premiumModel: PremiumViewModel,
                        private val trendsDBModel: TrendsDBViewModel,
                        context: Context,
): ViewModel() {

  private var fileState = 0

  init {
    viewModelScope.launch {
      fileState = getTrends(context)
    }
  }

  fun getFileState(): Int {
    return fileState
  }

  fun getTrendsModel(): TrendsDBViewModel {
    return trendsDBModel
  }

  fun getNetworkModel(): NetworkViewModel {
    return networkModel
  }

  fun getHistoryModel(): DailySearchHistoryViewModel {
    return historyModel
  }

  fun getSearchModel(): DailySearchViewModel {
    return dailySearchModel
  }

  fun getPremiumModel(): PremiumViewModel {
    return premiumModel
  }

  // 1 = First boot up
  // 0 = Old file
  // -1 create new file
  private suspend fun getTrends(context: Context): Int = withContext(Dispatchers.IO) { // to run code in Background Thread

    if (getNetworkModel().checkConnection(context)) {
      if (getTrendsModel().trends.count() == 0) {
        return@withContext 1
      } else if (getTrendsModel().trends.count() == 1) {

        if (fileIsOld(getTrendsModel().trends.asLiveData().value!![0].timestamp)) {
          return@withContext 0
        } else {
          return@withContext -1
        }
      }
    } else {
      getNetworkModel().toastMessage(context)
    }
    return@withContext -2
  }
}