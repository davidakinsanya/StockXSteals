package com.stockxsteals.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.http.Trend
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ServerViewModel: ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootTrends: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch(Dispatchers.Default) {
       val trends = getTrends()
       if (!trends.isNullOrEmpty()) _bootTrends.emit(trends)
    }
  }

  private suspend fun getTrends(): List<Trend>? = withContext(Dispatchers.IO) { // to run code in Background Thread
    val res = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
    return@withContext if (res.isSuccessful)
      res.body()!!
    else {
      Log.d("error", res.errorBody().toString())
      null
    }
  }
}