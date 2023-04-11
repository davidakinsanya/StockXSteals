package com.stockxsteals.server.dao.api

import com.stockxsteals.server.dto.api.Trend
import com.stockxsteals.server.http.RetrofitInstance

class TrendsDAO {
  
  fun getTrends(type: String,
                currency: String): List<Trend>? {
    val res = RetrofitInstance.trendsVariable.getTrends(type, currency).execute()
    
    return if (res.isSuccessful) res.body()!!
    else {
      println(res.errorBody())
      return null
    }
    
  }
}