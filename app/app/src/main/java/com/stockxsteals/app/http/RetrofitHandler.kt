package com.stockxsteals.app.http


import com.stockxsteals.app.model.http.Trend
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitHandler {

  @GET("/trends")
  fun getTrends(@Query("type") type: String,
                @Query("currency") currency: String): Call<List<Trend>>


  @GET("/")
  fun getSearch(@Query("search") search: String): Call<Map<String, List<String>>>
}