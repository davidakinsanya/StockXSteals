package com.stockxsteals.server.dao.ui

import com.stockxsteals.server.dao.api.ProductActivitySearchDAO
import com.stockxsteals.server.dto.api.ProductActivityData
import com.stockxsteals.server.dto.ui.Customer
import kotlin.collections.HashMap

class SneakerAnalysisDAO {
  private val type = listOf("buy", "ask", "sale")
  
  fun doAnalysis(customer: Customer?, slug: String, currency: String, country: String) {
    
    val sneaker = getSneaker(customer, slug, currency, country)
    val customerSize: String = mapShoeSize(customer!!).toString()
    val allTypesSizeMap = mutableListOf<HashMap<String, Int>>()
    var salesData: ProductActivityData?
  
    val completeMap = combineMaps(allTypesSizeMap)
    val sizePercentMap = HashMap<String, Double>()
    val popularSizeMap = HashMap<String, List<String>>()
    
    for (type in type) {
      if (type != "sale") {
        val productData = ProductActivitySearchDAO()
          .executeSearch(slug, type, currency, country)!!
        allTypesSizeMap.add(getSizeCount(productData))
      } else {
        salesData = ProductActivitySearchDAO()
          .executeSearch(slug, type, currency, country)!!
        allTypesSizeMap.add(getSizeCount(salesData))
      }
    }
    
    val totalSum = completeMap.values.stream().mapToInt { i -> i }.sum()
    val buyerSum = allTypesSizeMap[0].values.stream().mapToInt { i -> i }.sum()
    val bidderSum = allTypesSizeMap[1].values.stream().mapToInt { i -> i }.sum()
    val salesSum = allTypesSizeMap[2].values.stream().mapToInt { i -> i }.sum()
    
    val totalBool = completeMap.keys.contains(customerSize)
    val buyerBool = allTypesSizeMap[0].keys.contains(customerSize)
    val bidderBool = allTypesSizeMap[1].keys.contains(customerSize)
    val salesBool = allTypesSizeMap[2].keys.contains(customerSize)
    
    sizePercentMap["total"] = 0.0
    sizePercentMap["total"] = getPercent(totalBool, completeMap, customerSize, totalSum)!!
    
    sizePercentMap["buy"] = 0.0
    sizePercentMap["buy"] = getPercent(buyerBool, allTypesSizeMap[0], customerSize, buyerSum)!!
    
    sizePercentMap["bid"] = 0.0
    sizePercentMap["bid"] = getPercent(bidderBool, allTypesSizeMap[1], customerSize, bidderSum)!!
    
    sizePercentMap["sale"] = 0.0
    sizePercentMap["sale"] = getPercent(salesBool, allTypesSizeMap[2], customerSize, salesSum)!!
    
    
    popularSizeMap["total"] = listOf()
    popularSizeMap["total"] = getPopularSizes(completeMap)
    
    popularSizeMap["buy"] = listOf()
    popularSizeMap["buy"] = getPopularSizes(allTypesSizeMap[0])
    
    popularSizeMap["bid"] = listOf()
    popularSizeMap["bid"] = getPopularSizes(allTypesSizeMap[1])
    
    popularSizeMap["sale"] = listOf()
    popularSizeMap["sale"] = getPopularSizes(allTypesSizeMap[2])
    
    sneaker.size_percent = sizePercentMap
    sneaker.common_sizes = popularSizeMap
    
    /*
    val salesMap = HashMap<String, MutableList<Int>>()
    
    salesMap["9.5"] = mutableListOf()
    for (item in salesData!!.sales)
      if (item.size == "9.5")
        salesMap["9.5"]?.add(item.amount)
    
    println(salesMap.toString())
    
     */
  }
}