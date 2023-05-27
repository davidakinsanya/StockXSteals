package com.stockxsteals.app.viewmodel.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkViewModel(): ViewModel() {

  suspend fun checkConnection(context: Context): Boolean {
    return withContext(Dispatchers.IO) {
      val connectivityManager =
        context.applicationContext
          .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      val capability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
      return@withContext capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        ?: false
    }
  }

  fun toastMessage(context: Context) {
    Toast.makeText(context, "No internet connection.", Toast.LENGTH_LONG).show()
  }
}