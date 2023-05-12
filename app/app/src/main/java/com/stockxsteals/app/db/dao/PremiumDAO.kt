package com.stockxsteals.app.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PremiumDAO {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun setPremium(isPremium: Boolean)

  @Query("SELECT isPremium FROM premium")
  fun getIsPremium(): LiveData<Boolean>
}