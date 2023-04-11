package com.stockxsteals.server.dao.api

import com.stockxsteals.server.dto.api.SneakerSearchResult
import com.stockxsteals.server.http.RetrofitInstance

class GenericSearchDAO {
  
  fun executeSearch(productCode: String, limit: Int): List<SneakerSearchResult>? {
   
    val res = RetrofitInstance
      .sneakerSearchVariable
      .searchSneaker(productCode, limit)
      .execute()
    
    return if (res.isSuccessful) res.body()!!
    else {
      println("Error!")
      null
    }
  }
}



