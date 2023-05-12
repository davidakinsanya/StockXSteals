package com.stockxsteals.app.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stockxsteals.app.db.dao.DailySearchDAO
import com.stockxsteals.app.db.entity.DailySearch

@Database(entities = [DailySearch::class], version = 1, exportSchema = false)
abstract class DailySearchDatabase: RoomDatabase() {
  abstract fun dailySearchDAO(): DailySearchDAO

  companion object {
    private var INSTANCE: DailySearchDatabase? = null

    fun getDatabase(context: Context): DailySearchDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          DailySearchDatabase::class.java,
          "filter_presets"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }

}