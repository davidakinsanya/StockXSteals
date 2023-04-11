package com.stockxsteals.server.http

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This object sets up Retrofit allowing the API to make HTTP requests.
 *
 * @author david
 */
object RetrofitInstance {
  
  private const val base_url = "https://stockx1.p.rapidapi.com/v2/stockx/"
  
  val sneakerSearchVariable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(base_url)
      .build()
      .create(RetrofitHandler::class.java)
  }
  
  val productSearchVariable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(base_url)
      .build()
      .create(RetrofitHandler::class.java)
  }
    
  val productActivitySearchVariable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(base_url)
      .build()
      .create(RetrofitHandler::class.java)
  }
  
  val trendsVariable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(base_url)
      .build()
      .create(RetrofitHandler::class.java)
  }
  
  val product360Variable: RetrofitHandler by lazy {
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(base_url)
      .build()
      .create(RetrofitHandler::class.java)
  }
}