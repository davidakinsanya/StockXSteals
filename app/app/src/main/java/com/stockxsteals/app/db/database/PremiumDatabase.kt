package com.stockxsteals.app.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stockxsteals.app.db.dao.PremiumDAO
import com.stockxsteals.app.db.entity.Premium

@Database(entities = [Premium::class], version = 1, exportSchema = false)
abstract class PremiumDatabase: RoomDatabase() {
  abstract fun premiumDAO(): PremiumDAO

  companion object {
    private var INSTANCE: PremiumDatabase? = null

    fun getDatabase(context: Context): PremiumDatabase {
      val tempInstance = INSTANCE
      if (tempInstance != null) {
        return tempInstance
      }
      synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          PremiumDatabase::class.java,
          "filter_presets"
        ).build()
        INSTANCE = instance
        return instance
      }
    }
  }

}