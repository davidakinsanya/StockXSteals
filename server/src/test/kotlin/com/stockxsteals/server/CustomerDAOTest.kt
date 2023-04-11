package com.stockxsteals.server

import com.stockxsteals.server.db.CustomerDAO
import com.stockxsteals.server.dto.ui.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootTest
class CustomerDAOTest {
  
  @Autowired
  private val jdbcTemplate = JdbcTemplate()
  @Autowired
  private var customerDAO: CustomerDAO? = null
  private var testCustomer: Customer? = null
  private var DBCustomer: Customer? = null
  
  @BeforeEach
  fun setUpDatabase() {
    customerDAO = CustomerDAO(jdbcTemplate)
    
  }
  
  @Test
  fun Test1() {
    testCustomer = Customer.BUYER
    testCustomer!!.discord = "#0000"
    testCustomer!!.size = 7.0
    testCustomer!!.type = "uk"
    
    assertTrue(customerDAO!!.createCustomer(testCustomer!!))
  }
  
  @Test
  fun Test2() {
    assertNotNull(customerDAO!!.getCustomer("#0000"))
  }
  
  @Test
  fun Test3() {
    DBCustomer = customerDAO!!.getCustomer("#0000")
    
    assertEquals(DBCustomer!!.action, "buy")
    assertEquals(DBCustomer!!.discord, "#0000")
    assertEquals(DBCustomer!!.size, 7.0)
    assertEquals(DBCustomer!!.type, "uk")
  }
  
  @Test
  fun Test4() {
    assertTrue(customerDAO!!.deleteCustomer(customerDAO!!.getCustomer("#0000")!!.discord))
  }
}