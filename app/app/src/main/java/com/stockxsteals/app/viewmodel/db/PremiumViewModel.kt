package com.stockxsteals.app.viewmodel.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PremiumViewModel(application: Application): AndroidViewModel(application) {
  // private val readIsPremium: LiveData<Boolean>
  // private val repository: PremiumRepository

  init {}

  fun setPremium(isPremium: Boolean) {
    viewModelScope.launch {}
  }
}