package com.stockxsteals.server.dao.ui

import com.stockxsteals.server.dto.api.Product
import com.stockxsteals.server.dto.ui.Availability
import com.stockxsteals.server.dto.ui.Customer
import com.stockxsteals.server.dto.ui.Sneaker

class SneakerDAO {
  
  
  fun returnSneaker(product: Product, customer: Customer): Sneaker {
    
    if (customer.size != 0.0) {
      for (variant in product.variants) {
        for (size in variant.sizes) {
          if (customer.type == size.type && size.size.contains(customer.size.toString())) {
            
            val asks = variant.market.bids.num_asks
            val bids = variant.market.bids.num_bids
            val diff = asks - bids
            
            return Sneaker(product.name,
              product.sku,
              product.slug,
              product.description,
              variant.market.sales.last_sale,
              variant.market.sales.last_sale * (1 + (variant.market.sales.volatility)),
              getAvailability(asks, bids),
              diff,
              null,
              null,
            )
          }
        }
      }
    }
    
    val asks = product.market.bids.num_asks
    val bids = product.market.bids.num_bids
    val diff = asks - bids
    
    return Sneaker(product.name,
      product.sku,
      product.slug,
      product.description,
      product.market.sales.last_sale,
      product.market.sales.last_sale * (1 + (product.market.sales.volatility)),
      getAvailability(asks, bids),
      diff,
      null,
      null,
    )
  }
  
  private fun getAvailability(asks: Int, bids: Int): Availability {
    return if (asks > bids) Availability.SURPLUS
    else Availability.SHORTAGE
  }
}