package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.stockxsteals.app.http.ApiService
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
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


   suspend fun accessTrends(context: Context): Int = withContext(Dispatchers.IO) { // to run code in Background Thread

    val service = ApiService.create()
    val data: List<Trend> = service.getTrends("sneakers", "EUR")
    addTrend(data)

    return@withContext 1
  }

  /*
Klaxon().parse<List<Trend>>(
        trendsModel.getTrendsModel().trends.collectAsState(
          initial = emptyList()
        ).value[0].json
      )!!
 */

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