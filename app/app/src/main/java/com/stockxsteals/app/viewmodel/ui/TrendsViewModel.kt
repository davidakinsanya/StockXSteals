package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.utils.writeCurrentTrends
import com.stockxsteals.app.viewmodel.db.DailySearchHistoryViewModel
import com.stockxsteals.app.viewmodel.db.DailySearchViewModel
import com.stockxsteals.app.viewmodel.db.PremiumViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.File

class TrendsViewModel(context: Context,
                      private val networkModel: NetworkViewModel,
                      private val historyModel: DailySearchHistoryViewModel,
                      private val dailySearchModel: DailySearchViewModel,
                      private val premiumModel: PremiumViewModel): ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootTrends: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch(Dispatchers.Default) {
        //val file = File(context.filesDir, "/trends/obj")
       //val trends = getTrends(file, context)
      //if (!trends.isNullOrEmpty()) _bootTrends.emit(trends)
    }
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

  private suspend fun getTrends(fileLocation: File, context: Context): List<Trend>? = withContext(Dispatchers.IO) { // to run code in Background Thread
    var result: Response<List<Trend>>? = null

    withContext(Dispatchers.IO) {
      if (getNetworkModel().checkConnection(context)) {
          result = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
      } else {
        getNetworkModel().toastMessage(context)
      }
    }

    return@withContext if (result != null && result!!.isSuccessful) {
      writeCurrentTrends(fileLocation.toString(), result!!.body()!!)
      result!!.body()!!
    } else {
      Log.d("error", result!!.errorBody().toString())
      null
    }
  }
}
