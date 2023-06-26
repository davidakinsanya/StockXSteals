package com.stockxsteals.app.viewmodel.ui

import androidx.lifecycle.ViewModel
import com.beust.klaxon.Klaxon
import com.stockxsteals.app.http.ApiService
import com.stockxsteals.app.model.dto.Trend
import com.stockxsteals.app.utils.fileIsOld
import com.stockxsteals.app.utils.getCurrentDate
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.TrendsDBViewModel
import db.entity.Trends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class TrendsUIViewModel(private val networkModel: NetworkViewModel,
                        private val historyModel: DailySearchHistoryViewModel,
                        private val dailySearchModel: DailySearchViewModel,
                        private val trendsDBModel: TrendsDBViewModel,
): ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(emptyList())
  private val _backUpTrends = MutableStateFlow<List<Trend>>(emptyList())
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


  suspend fun accessTrends(trends: List<Trends>) =
    withContext(Dispatchers.IO) { // to run code in Background Thread
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
      _backUpTrends.emit(trends)
    }
  }

  private suspend fun addFilterTrend(trends: List<Trend>) {
    withContext(Dispatchers.IO) {
      _bootTrends.emit(trends)
    }
  }

  suspend fun addDummyTrend(trends: List<Trend>) {
    withContext(Dispatchers.Default) {
      _bootTrends.emit(trends)
      _backUpTrends.emit(trends)
    }
  }

  suspend fun resetTrends() {
    withContext(Dispatchers.IO) {
      _bootTrends.emit(_backUpTrends.value)
    }
  }

    suspend fun filterTrends(text: String) {
      var count = 0
      withContext(Dispatchers.IO) {
        val filteredTrends: MutableList<Trend> = mutableListOf()
        if (text.isNotEmpty()) {
          bootTrends.value.forEach { trend ->
            if (trend.name.lowercase().contains(text.lowercase())) {
              count++
              filteredTrends.add(trend)
            }
          }
          if (count != 0)
            addFilterTrend(filteredTrends)
        }
      }
    }
  }