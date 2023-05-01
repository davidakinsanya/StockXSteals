package com.stockxsteals.app.http


import com.stockxsteals.app.model.http.Trend
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitHandler {

  @GET
  fun getTrends(@Query("query") slug: String,
                @Query("currency") currency: String): Call<List<Trend>>


}