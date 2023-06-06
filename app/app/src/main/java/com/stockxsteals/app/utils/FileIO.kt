package com.stockxsteals.app.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.Clock
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone

fun getCurrentDate(): String {
  val now: Instant = Clock.System.now()
  return now.toLocalDateTime(TimeZone.currentSystemDefault()).toString()
}

fun sameDateCheck(timestamp: String): Boolean {
  val stampDate = timestamp.split("T")[0].split("-")
  val currentDate = getCurrentDate().split("T")[0].split("-")
  return currentDate.toString().equals(stampDate)
}

fun fileIsOld(timestamp: String): Boolean {
  val stampSplit = timestamp.split("T")
  val stampArr = stampSplit[1].split(":")
  val stampDateArr = stampSplit[0].split("-")

  val currentDate = getCurrentDate().split("T")
  val currentTimeArr = currentDate[1].split(":")
  val currentDateArr = currentDate[0].split("-")

  return currentTimeArr[0].toInt() - stampArr[0].toInt() >= 3 ||
          currentDateArr[2].toInt() - stampDateArr[2].toInt() != 0
}