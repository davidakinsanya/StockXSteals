package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.utils.fileIsOld
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TrendsUIViewModel(private val networkModel: NetworkViewModel,
                        private val historyModel: DailySearchHistoryViewModel,
                        private val dailySearchModel: DailySearchViewModel,
                        private val premiumModel: PremiumViewModel,
                        private val trendsDBModel: TrendsDBViewModel,
                        context: Context,
): ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  val bootTrends: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch {
      //val trends = getTrends(context)
      //if (!trends.isNullOrEmpty()) _bootTrends.emit(trends)
    }
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

  private suspend fun getTrends(context: Context): List<Trend>? = withContext(Dispatchers.IO) { // to run code in Background Thread

    var result: Response<List<Trend>>? = null

    if (getNetworkModel().checkConnection(context)) {

      if (getTrendsModel().trends.count() == 0) {

        result = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
        getTrendsModel().setFirstTrend(getCurrentDate(), result!!.body()!!.toString())

      } else if (getTrendsModel().trends.count() == 1) {

        if (fileIsOld(getTrendsModel().trends.asLiveData().value!![0].timestamp)) {

          result = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
          getTrendsModel().updateTrends(getCurrentDate(), result!!.body()!!.toString(), 0)

        } else {
          return@withContext listOf()
        }
      }
    } else {
      getNetworkModel().toastMessage(context)
    }
    return@withContext if (result!!.body() != null) result.body()!! else null
    }
  }
