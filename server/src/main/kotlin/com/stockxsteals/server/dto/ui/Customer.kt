package com.stockxsteals.server.dto.ui

enum class Customer(var discord: String,
                    val action: String,
                    var size: Double,
                    var type: String) {
  
  BUYER("", "buy", 0.0, ""),
  SELLER("","sell", 0.0, "")
}