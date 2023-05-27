package com.stockxsteals.app.viewmodel.db

import androidx.lifecycle.ViewModel
import com.stockxsteals.app.datasource.intrface.TrendsDataSource
import db.entity.Trends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TrendsDBViewModel (
  private val trendsDataSource: TrendsDataSource
  ): ViewModel()  {

    val trends = getAllTrends()

    private fun getAllTrends(): Flow<List<Trends>> {
      return trendsDataSource.getAllTrends()
    }

    suspend fun updateTrends(timestamp: String, json: String, id: Long) {
      withContext(Dispatchers.IO) {
        trendsDataSource.updateTrends(timestamp, json, id)
      }
    }

    suspend fun setFirstTrend(timestamp: String, json: String) {
      withContext(Dispatchers.IO) {
        trendsDataSource.setFirstTrend(timestamp, json)
      }
    }
}