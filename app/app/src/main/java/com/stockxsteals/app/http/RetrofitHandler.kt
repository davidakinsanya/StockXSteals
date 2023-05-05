package com.stockxsteals.app.http


import com.stockxsteals.app.model.http.Trend
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitHandler {

  companion object {
    const val api_key = "fdde012a2emsh84b64c2a982e062p1ee167jsn7f2379a2c999"
    const val api_host = "stockx1.p.rapidapi.com"
  }

  @Headers("X-RapidAPI-Key: $api_key", "X-RapidAPI-Host: $api_host")
  @GET("trends")
  fun getTrends(@Query("query") type: String,
                @Query("currency") currency: String): Call<List<Trend>>

  @GET("/")
  fun getSearch(@Query("search") search: String): Call<Map<String, List<String>>>
}