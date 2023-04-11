package com.stockxsteals.server.dao.ui

import com.stockxsteals.server.dao.api.ProductSearchDAO
import com.stockxsteals.server.dto.api.ProductActivity
import com.stockxsteals.server.dto.api.ProductActivityData
import com.stockxsteals.server.dto.ui.Customer
import com.stockxsteals.server.dto.ui.Sneaker
import java.util.*
import kotlin.collections.HashMap

fun getSneaker(customer: Customer?,
               slug: String,
               currency: String,
               country: String): Sneaker {
  
  val product = ProductSearchDAO().executeSearch(slug, currency, country)
  return SneakerDAO().returnSneaker(product!!, customer!!)
}

fun getPopularSizes(map: Map<String, Int>) : List<String> {
  val orderedMap = map.values.sortedDescending()
  val popularSizes = mutableListOf<String>()
  
  for (key in map.keys)
    for (i in 0..4)
      if (map[key]?.equals(orderedMap[i])!!) popularSizes.add(key)
  
  return popularSizes
}

fun getPercent(bool: Boolean,
                       map: Map<String, Int>,
                       size: String,
                       sum: Int): Double? {
  return if (bool)
    ((map[size]?.toDouble()?.div(sum))?.times(100))
  else null
}

fun combineMaps(map: List<HashMap<String, Int>>): Map<String, Int> {
  return ( map[0].asSequence()
          + map[1].asSequence()
          + map[2].asSequence())
    .groupBy({it.key}, {it.value})
    .mapValues { (_,values) -> values.sum() }
}

fun getSizeCount(product: ProductActivityData): HashMap<String, Int> {
  val map = HashMap<String, Int>()
  
  if (product.bids.isNotEmpty()) addToMap(product.bids, map)
  else if (product.asks.isNotEmpty()) addToMap(product.asks, map)
  else addToMap(product.sales, map)
  
  return map
}

fun addToMap(activity: List<ProductActivity>,
                     map: HashMap<String, Int>) {
  val list  = mutableListOf<String>()
  
  for (item in activity) list.add(item.size)
  for (item in list.distinct()) map[item] = Collections.frequency(list, item)
}

fun mapShoeSize(customer: Customer): Double {
  var us: Double = customer.size
  
  when (customer.type) {
    "uk" -> {
      us = "%.1f".format(customer.size.minus(1)).toDouble()
    }
    
    "jp" -> {
      us = "%.1f".format(customer.size.minus(19)).toDouble()
    }
    
    "kr" -> {
      us = "%.1f".format(customer.size.div(10).minus(19)).toDouble()
    }
    
    "eu" -> {
      val footLength = (customer.size.minus(2)) * (2/3)
      val footToInches = footLength.div(2.54)
      us = "%.1f".format(footToInches * 3 - 22).toDouble()
    }
    
    "us w" -> {
      us = "%.1f".format(customer.size.minus(1.5)).toDouble()
    }
  }
  return us
}