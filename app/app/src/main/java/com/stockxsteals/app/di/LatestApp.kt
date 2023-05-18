package com.stockxsteals.app.di

import android.app.Application
import com.qonversion.android.sdk.Qonversion
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LatestApp: Application() {
  override fun onCreate() {
    super.onCreate()
    Qonversion.setDebugMode()
    Qonversion.launch(this,
      "jCMdbPE5u0fYo1IBuygpK1qb5s6RI_EK",
      false)
  }
}