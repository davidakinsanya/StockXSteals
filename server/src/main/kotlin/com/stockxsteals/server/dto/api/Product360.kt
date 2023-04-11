package com.stockxsteals.server.dto.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product360(@SerializedName("results") @Expose val results: List<String>)
