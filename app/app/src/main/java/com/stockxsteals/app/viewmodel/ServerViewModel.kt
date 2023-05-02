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

class ServerViewModel: ViewModel() {

  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootMap: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch(Dispatchers.Default) {
      val trends = getTrends()
      if (!trends.isNullOrEmpty())
        _bootTrends.emit(trends)
    }
  }


  private fun getTrends(): List<Trend>? {
    val res = RetrofitInstance.trend.getTrends("sneaker", "USD").execute()
    return if (res.isSuccessful)
      res.body()!!
    else {
      Log.d("error", res.errorBody().toString())
      null
    }
  }
}