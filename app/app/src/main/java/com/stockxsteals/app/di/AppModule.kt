package com.stockxsteals.app.di

import android.app.Application
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.impl.*
import com.stockxsteals.app.datasource.intrface.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideSqlDriver(app: Application): SqlDriver {
    return AndroidSqliteDriver(
      schema = Database.Schema,
      context = app,
      name = "L8test.db"
    )
  }

  @Provides
  @Singleton
  fun provideDailySearchDataSource(driver: SqlDriver): DailySearchDataSource {
    return DailySearchImpl(Database(driver))
  }

  @Provides
  @Singleton
  fun provideFilterPresetDataSource(driver: SqlDriver): FilterPresetDataSource {
    return FilterPresetImpl(Database(driver))
  }

  @Provides
  @Singleton
  fun providePremiumDataSource(driver: SqlDriver): PremiumDataSource {
    return PremiumImpl(Database(driver))
  }

  @Provides
  @Singleton
  fun provideDailySearchHistoryDataSource(driver: SqlDriver): DailySearchHistorySource {
    return DailySearchHistoryImpl(Database(driver))
  }

  @Provides
  @Singleton
  fun provideTrendsDataSource(driver: SqlDriver): TrendsDataSource {
    return TrendsImpl(Database(driver))
  }
}