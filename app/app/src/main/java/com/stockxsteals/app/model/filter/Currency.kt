package com.stockxsteals.app.model.filter

enum class Currency(val type: String, val symbol: String) {
  GBP("GBP","£"),
  USD("USD", "$"),
  JPY("JPY", "¥"),
  EUR("EUR","€"),
  CAD("CA$", "CA$"),
  AUD("AU$", "AU$")
} // TODO: Figure how to show user the currency.