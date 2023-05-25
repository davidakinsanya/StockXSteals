package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkViewModel(): ViewModel() {

  suspend fun checkConnection(context: Context): Boolean {
    return withContext(Dispatchers.IO) {
      val connectivityManager =
        Contexts.getApplication(context.applicationContext)
          .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
      return@withContext capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ?: false
    }
  }
}