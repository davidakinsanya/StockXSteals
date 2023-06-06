package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.PremiumDataSource
import db.entity.Premium
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PremiumViewModel (
  private val premiumDataSource: PremiumDataSource
): ViewModel() {

    val premiumQuotas = getQuotas()

   private fun getQuotas(): Flow<List<Premium>> {
     return premiumDataSource.getPremiumQuotas()
   }

   suspend fun newPremiumQuota() {
     withContext(Dispatchers.IO) {
       premiumDataSource.newPremiumQuota()
     }
   }

   suspend fun getIsPremium(id: Int): Int {
     return withContext(Dispatchers.IO) {
       premiumDataSource.getIsPremium(id.toLong())
     }
   }

   suspend fun setIsPremium(isPremium: Int) {
     withContext(Dispatchers.IO) {
       premiumDataSource.setIsPremium(isPremium)
     }
   }
}