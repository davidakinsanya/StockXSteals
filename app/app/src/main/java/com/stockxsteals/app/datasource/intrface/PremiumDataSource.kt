package com.stockxsteals.app.datasource.intrface


interface PremiumDataSource {

  suspend fun getIsPremium(): Int

  suspend fun setIsPremium(isPremium: Int)
}