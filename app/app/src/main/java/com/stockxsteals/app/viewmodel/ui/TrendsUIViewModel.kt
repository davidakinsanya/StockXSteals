package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
import db.entity.Trends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TrendsUIViewModel(context: Context,
                        private val networkModel: NetworkViewModel,
                        private val historyModel: DailySearchHistoryViewModel,
                        private val dailySearchModel: DailySearchViewModel,
                        private val premiumModel: PremiumViewModel,
                        private val recentTrendsModel: TrendsDBViewModel,
                        private val dbTrend: Trends): ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootTrends: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch(Dispatchers.Default) {
       //val trends = getTrends(context, trend)
      //if (!trends.isNullOrEmpty()) _bootTrends.emit(trends)
    }
  }
  fun getDBTrend(): Trends {
    return dbTrend
  }
  fun getTrendsModel(): TrendsDBViewModel {
    return recentTrendsModel
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

  private suspend fun getTrends(context: Context,
                                trends: Trends): List<Trend>? = withContext(Dispatchers.IO) { // to run code in Background Thread

    var result: Response<List<Trend>>? = null

    withContext(Dispatchers.IO) {
      if (getNetworkModel().checkConnection(context)) {
          result = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
      } else {
        getNetworkModel().toastMessage(context)
      }
    }

    return@withContext if (result != null && result!!.isSuccessful) {
      val response =  result!!.body()!!
      getTrendsModel().updateTrends(getCurrentDate(), response.toString(), trends.id)
      response
    } else {
      Log.d("error", result!!.errorBody().toString())
      null
    }
  }
}
