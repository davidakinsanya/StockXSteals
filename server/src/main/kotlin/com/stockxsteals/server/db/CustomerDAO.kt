package com.stockxsteals.server.db

import com.stockxsteals.server.dto.ui.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet
import java.sql.SQLException

@Repository
class CustomerDAO(@Autowired val jdbcTemp: JdbcTemplate) {
  
  fun createCustomer(customer: Customer): Boolean {
    val sql = "INSERT INTO Customer (discord, " +
                                     "customerAction, " +
                                     "size, " +
                                     "sizeType) VALUES (?,?,?,?)"
    
    return jdbcTemp.update(sql, customer.discord,
                           customer.action,
                           customer.size,
                           customer.type) > 0
  }
  
  fun getCustomer(discord: String): Customer? {
    val sql = "SELECT * FROM Customer WHERE discord = \"$discord\""
    var customer: Customer? = null
    val obj = jdbcTemp.queryForObject(sql) { rs: ResultSet, _: Int -> customer = customerMapper(rs) }
    return customer
  }
  
  fun getAllCustomers(): List<Customer>? {
    val sql = "SELECT * FROM Customer"
    return jdbcTemp.query(sql) {rs: ResultSet, _: Int -> customerMapper(rs)}
  }
  
  fun deleteCustomer(discord: String): Boolean {
    val sql = "DELETE FROM CUSTOMER WHERE discord = \"$discord\""
    return jdbcTemp.update(sql) > 0
  }
  
  fun customerMapper(rs: ResultSet): Customer? {
    try {
      val customer = if (rs.getString("customerAction") == "buy") Customer.BUYER
      else Customer.SELLER
    
      customer.discord = rs.getString("discord")
      customer.size = rs.getDouble("size")
      customer.type = rs.getString("sizeType")
    
      return customer
    } catch (sql: SQLException) { return null }
  }

}