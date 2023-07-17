package com.stockxsteals.app.utils

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun payWallView(firebase: FirebaseAnalytics) {
  val bundle = Bundle()
  bundle.putString("PAYWALL_VIEW", "true")
  firebase.logEvent("PAYWALL_VIEW", bundle)
}

fun productView(firebase: FirebaseAnalytics) {
  val bundle = Bundle()
  bundle.putString("PRODUCT_VIEW", "true")
  firebase.logEvent("PRODUCT_VIEW", bundle)
}

fun conversionEvent(firebase: FirebaseAnalytics) {
  val bundle = Bundle()
  bundle.putString("CONVERSION_EVENT", "true")
  firebase.logEvent("CONVERSION_EVENT", bundle)
}

fun errorEvent(firebase: FirebaseAnalytics, code: Int) {
  val bundle = Bundle()
  bundle.putString("ERROR_EVENT_$code", "true")
  firebase.logEvent("ERROR_EVENT", bundle)
}


fun conversionErrorEvent(firebase: FirebaseAnalytics) {
  val bundle = Bundle()
  bundle.putString("CONVERSION_ERROR_EVENT", "true")
  firebase.logEvent("CONVERSION_ERROR_EVENT", bundle)
}