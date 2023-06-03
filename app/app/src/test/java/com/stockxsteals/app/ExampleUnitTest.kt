package com.stockxsteals.app

import com.stockxsteals.app.http.ApiService
import com.stockxsteals.app.model.dto.Product
import com.stockxsteals.app.model.dto.blankProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


  @Test
 fun addition_isCorrect() {
    val res = runBlocking { getProduct() }
    assertNotEquals(res.brand, "")
  }
}


suspend fun getProduct(): Product {
  var res: Product = blankProduct()
  withContext(Dispatchers.IO) {
    val service = ApiService.create()
    res = service.searchProduct(
      "slug=nike-air-max-1-travis-scott-wheat",
      "EUR",
      "FR")
  }
  return res
}
