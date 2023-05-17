package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stockxsteals.app.http.RetrofitInstance
import com.stockxsteals.app.model.dto.*
import com.stockxsteals.app.utils.writeCurrentTrends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import java.io.File

class TrendsViewModel(context: Context): ViewModel() {
  private val _bootTrends = MutableStateFlow<List<Trend>>(listOf())
  var bootTrends: StateFlow<List<Trend>> = _bootTrends

  init {
    viewModelScope.launch(Dispatchers.Default) {
        //val file = File(context.filesDir, "/trends/obj")
       //val trends = getTrends(file)
      //if (!trends.isNullOrEmpty()) _bootTrends.emit(trends)
    }
  }

  private suspend fun getTrends(fileLocation: File): List<Trend>? = withContext(Dispatchers.IO) { // to run code in Background Thread
    val res = RetrofitInstance.trend.getTrends("sneakers", "EUR").execute()
    return@withContext if (res.isSuccessful) {
      writeCurrentTrends(fileLocation.toString(), res.body()!!)
      res.body()!!
    } else {
      Log.d("error", res.errorBody().toString())
      null
    }
  }
}
