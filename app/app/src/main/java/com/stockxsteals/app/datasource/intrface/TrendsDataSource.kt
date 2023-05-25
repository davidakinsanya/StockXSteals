package com.stockxsteals.app.datasource.intrface

import db.entity.Trends
import kotlinx.coroutines.flow.Flow

interface TrendsDataSource {
  fun getAllTrends(): Flow<List<Trends>>

  suspend fun updateTrends(timestamp: String, json: String, id: Long)

  suspend fun setFirstTrend(timestamp: String, json: String)
}