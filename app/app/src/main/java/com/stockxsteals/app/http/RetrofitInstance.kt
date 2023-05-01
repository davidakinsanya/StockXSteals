package com.stockxsteals.app.http

import com.stockxsteals.app.model.http.Trend
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
  private const val url = "http://localhost:8080"

  val trend: Trend by lazy {
    Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(Trend::class.java)
  }


}