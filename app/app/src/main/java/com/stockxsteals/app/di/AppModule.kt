package com.stockxsteals.app.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.stockxsteals.app.Database
import com.stockxsteals.app.datasource.impl.*
import com.stockxsteals.app.datasource.intrface.*
import com.stockxsteals.app.viewmodel.db.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

  single {
    Database(get())
  }

  single<SqlDriver> {
    AndroidSqliteDriver(
        schema = Database.Schema,
        context = this.androidContext(),
        name = "L8test.db"
      )
  }

  viewModel {
    FilterPresetsViewModel(get())
  }

  single<FilterPresetDataSource> {
    FilterPresetImpl(get())
  }

  viewModel {
    DailySearchViewModel(get())
  }

  single<DailySearchDataSource> {
    DailySearchImpl(get())
  }

  viewModel {
    PremiumViewModel(get())
  }

  single<PremiumDataSource> {
    PremiumImpl(get())
  }

  viewModel {
    DailySearchHistoryViewModel(get())
  }

  single<DailySearchHistorySource> {
    DailySearchHistoryImpl(get())
  }

  viewModel {
    TrendsDBViewModel(get())
  }


  single<TrendsDataSource> {
    TrendsImpl(get())
  }
}