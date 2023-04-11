package com.stockxsteals.server.controller

import com.stockxsteals.server.db.CustomerDAO
import com.stockxsteals.server.dao.ui.SneakerAnalysisDAO
import com.stockxsteals.server.dto.ui.Customer
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import retrofit2.http.Query

@RestController
class UIController {
  private val jdbcTemplate = JdbcTemplate()
  
  @GetMapping("newCustomer")
  fun makeCustomer(@Query("discord") discord: String,
                   @Query("action") action: String,
                   @Query("size") size: Double,
                   @Query("type") type: String): Boolean {
    
    val customer = if (action == "buy") Customer.BUYER
    else Customer.SELLER
    
    customer.discord = discord
    customer.size = size
    customer.type = type
    
    return CustomerDAO(jdbcTemplate).createCustomer(customer)
  }
  
  @GetMapping("analysis")
  fun doAnalysis(@Query("discord") discord: String,
                 @Query("slug") slug: String,
                 @Query("type") type: String,
                 @Query("currency") currency: String,
                 @Query("country") country: String) {
    
    val customer = CustomerDAO(jdbcTemplate).getCustomer(discord)
    SneakerAnalysisDAO().doAnalysis(customer, slug, currency, country)
  }
}