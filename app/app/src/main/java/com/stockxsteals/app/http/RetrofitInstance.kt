package com.stockxsteals.app.http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
  private const val url = "http://localhost:8080"

  val trend: RetrofitHandler by lazy {
    Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RetrofitHandler::class.java)
  }


}