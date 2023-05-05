package com.stockxsteals.app.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object RetrofitInstance {

  private const val url = "https://stockx1.p.rapidapi.com/v2/stockx/"
  private const val search_url = "http://192.168.1.127:5000"
  private val OkHttp = OkHttpClient.Builder().callTimeout(Duration.ofSeconds(5L))

  val trend: RetrofitHandler by lazy {
    Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RetrofitHandler::class.java)
  }

  val filterSearch: RetrofitHandler by lazy {
    Retrofit.Builder()
      .baseUrl(search_url)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RetrofitHandler::class.java)

  }


}