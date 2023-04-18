package com.stockxsteals.app.model.filter


class Country() {
  fun getCountry(): List<String> {
    return java.util.Locale.getISOCountries().asList()
  }
}
