package com.stockxsteals.app.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

  private const val stockx_api = "https://stockx1.p.rapidapi.com/v2/stockx/"
  private const val search_url = "http://192.168.1.127:5000"

  val trend: RetrofitHandler by lazy {
    Retrofit.Builder()
      .baseUrl(stockx_api)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RetrofitHandler::class.java)
  }

  val filterSearch: RetrofitHandler by lazy {
    Retrofit.Builder()
      .baseUrl(search_url)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(RetrofitHandler::class.java)

  }

  val productSearchVariable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(stockx_api)
      .build()
      .create(RetrofitHandler::class.java)
  }


}