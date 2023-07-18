package com.stockxsteals.app.di

import android.app.Application
import com.qonversion.android.sdk.Qonversion
import com.qonversion.android.sdk.QonversionConfig
import com.qonversion.android.sdk.dto.QLaunchMode
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class LatestApp: Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      modules(appModule)
      androidContext(this@LatestApp)
    }
    val qonversionConfig = QonversionConfig.Builder(
      this,
      "jCMdbPE5u0fYo1IBuygpK1qb5s6RI_EK",
      QLaunchMode.SubscriptionManagement
    ).build()
    Qonversion.initialize(qonversionConfig)
  }
}