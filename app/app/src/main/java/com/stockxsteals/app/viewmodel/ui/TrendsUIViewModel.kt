package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
      println(trends.size)
     // trendsOnDb.isNullOrEmpty() -- set first trend
    /*
    val service = ApiService.create()
    val trends = service.getTrends()

     if (trendsOnDb?.isEmpty() == true) {
       val data: List<Trend> = trends.value!!
       getTrendsModel().setFirstTrend(getCurrentDate(), data.toString())
       addTrend(data)
     } else {
       if (fileIsOld(trendsOnDb!![0].timestamp)) {

         val data: List<Trend> = trends.value!!
         getTrendsModel().updateTrends(getCurrentDate(), data.toString(), 0)
       } else {

         val data = Klaxon().parse<List<Trend>>(
           trendsOnDb[0].json
         )!!
         addTrend(data)
       }
     }

      */
  }

   suspend fun addTrend(trends: List<Trend>) {
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