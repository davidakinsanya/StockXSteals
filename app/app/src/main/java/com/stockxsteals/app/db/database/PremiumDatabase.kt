package com.stockxsteals.app.db.database


import com.stockxsteals.app.db.dao.PremiumDAO



abstract class PremiumDatabase() {
  abstract fun premiumDAO(): PremiumDAO

  companion object {

  }

}