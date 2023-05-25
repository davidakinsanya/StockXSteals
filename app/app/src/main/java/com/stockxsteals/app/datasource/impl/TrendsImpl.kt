package com.stockxsteals.app.datasource.impl

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.intrface.TrendsDataSource
import db.entity.Trends
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TrendsImpl(db: Database): TrendsDataSource {
  private val queries = db.trendsQueries

  override fun getAllTrends(): Flow<List<Trends>> {
    return queries.getAllTrends().asFlow().mapToList()
  }

  override suspend fun updateTrends(timestamp: String, json: String, id: Long) {
    withContext(Dispatchers.IO) {
      queries.updateTrends(timestamp, json, id)
    }
  }

  override suspend fun setFirstTrend(timestamp: String, json: String) {
    withContext(Dispatchers.IO) {
      queries.setFirstTrend(timestamp, json)
    }
  }

}