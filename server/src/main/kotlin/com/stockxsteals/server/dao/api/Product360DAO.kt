package com.stockxsteals.server.dao.api

import com.stockxsteals.server.dto.api.Product360
import com.stockxsteals.server.http.RetrofitInstance

class Product360DAO {
  
  fun executeSearch(slug: String): Product360? {
    val res = RetrofitInstance
      .product360Variable
      .getProduct360(slug)
      .execute()
    
    return if (res.isSuccessful) res.body()!!
    else {
      print(res.errorBody())
      return null
    }
    
  }
}