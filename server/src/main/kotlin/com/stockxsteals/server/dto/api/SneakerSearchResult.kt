package com.stockxsteals.server.dto.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class SneakerSearchResult(@SerializedName("id") @Expose val id: String,
                               @SerializedName("slug") @Expose val slug: String,
                               @SerializedName("sku") @Expose val sku: String,
                               @SerializedName("name") @Expose val name: String,
                               @SerializedName("brand") @Expose val brand: String,
                               @SerializedName("image") @Expose val image_url: String) {
  
  
  override fun toString(): String {
    return "SneakerSearchResult(id='$id', slug='$slug', sku='$sku', name='$name', brand='$brand', image_url='$image_url')"
  }
}
