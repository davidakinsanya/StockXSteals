package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.beust.klaxon.Klaxon
import com.stockxsteals.app.http.ApiService
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.fileIsOld
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
import db.entity.Trends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class TrendsUIViewModel(private val networkModel: NetworkViewModel,
                        private val historyModel: DailySearchHistoryViewModel,
                        private val dailySearchModel: DailySearchViewModel,
                        private val premiumModel: PremiumViewModel,
                        private val trendsDBModel: TrendsDBViewModel,
): ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootTrends: StateFlow<List<Trend>> = _bootTrends

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


   suspend fun accessTrends(trends: List<Trends>) = withContext(Dispatchers.IO) { // to run code in Background Thread
     val service = ApiService.create()

     if (trends.isEmpty()) {
       val newTrends = service.getTrends("sneakers", "EUR")
       val newTrendsJson = Klaxon().toJsonString(newTrends)
       getTrendsModel().setFirstTrend(getCurrentDate(), newTrendsJson)
       addTrend(newTrends)
     } else {
       if (fileIsOld(trends[0].timestamp)) {
         val newTrends = service.getTrends("sneakers", "EUR")
         val newTrendsJson = Klaxon().toJsonString(newTrends)
         getTrendsModel().updateTrends(getCurrentDate(), newTrendsJson, 1)
         addTrend(newTrends)
       } else {
         val data = Klaxon().parseArray<Trend>(
           trends[0].json
         )!!
         addTrend(data)
       }
     }
  }

   private suspend fun addTrend(trends: List<Trend>) {
     withContext(Dispatchers.IO) {
       _bootTrends.emit(trends)
     }
  }

  suspend fun addDummyTrend(trends: List<Trend>) {
    withContext(Dispatchers.IO) {
      _bootTrends.emit(trends)
    }
  }

  fun filterTrends(text: String) {
    if (text.isNotEmpty()) {
      bootTrends.value.filter { trend ->
        trend.name.contains(text)
      }
    }
  }
}