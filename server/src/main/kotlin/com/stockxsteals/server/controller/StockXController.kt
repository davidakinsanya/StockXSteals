package com.stockxsteals.server.controller

import com.stockxsteals.server.dao.api.*
import com.stockxsteals.server.dto.api.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.http.Query

@RestController
class StockXController {
  
  @GetMapping("/search")
  fun sneakerSearch(@Query("productCode") productCode: String,
                    @Query("limit") limit: Int): List<SneakerSearchResult>? {
    
    return GenericSearchDAO().executeSearch(productCode, limit)
  }
  
  @GetMapping("/product")
  fun productSearch(@Query("slug") slug: String,
                    @Query("currency") currency: String,
                    @Query("country") country: String): Product? {
    
    return ProductSearchDAO().executeSearch(slug, currency, country)
  }
  
  @GetMapping("/product/activity")
  fun productActivitySearch(@Query("slug") slug: String,
                            @Query("type") type: String,
                    @Query("currency") currency: String,
                    @Query("country") country: String): ProductActivityData? {
    
    
    return ProductActivitySearchDAO().executeSearch(slug, type, currency, country)
  }
  
  @GetMapping("/trends")
  fun getTrends(@Query("type") type: String,
                @Query("currency") currency: String): List<Trend>? {
    
    return TrendsDAO().getTrends(type, currency)
  }
  
  @GetMapping("/product/360")
  fun getProduct360(@Query("slug") slug: String): Product360? {
    return Product360DAO().executeSearch(slug)
  }
  
}