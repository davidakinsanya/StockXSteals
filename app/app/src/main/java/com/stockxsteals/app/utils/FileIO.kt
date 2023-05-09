package com.stockxsteals.app.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stockxsteals.app.model.dto.Trend
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.time.LocalDateTime


fun writeCurrentTrends(trend: Trend) {
  val trendsFile = "current trends : " + LocalDateTime.now()

  try {
    val writer = FileWriter(File(trendsFile))
    writer.use { Gson().toJson(trend) }
    writer.close()
  } catch (e : IOException){
    Log.e("err","not fn", e)
  }
}

fun readCurrentTrends(file: String): Trend? {
  if (!fileIsOld(file)) {
    val trend = object : TypeToken<Trend>() {}.type
    val fileVariable: File = File(file)
    return Gson().fromJson(fileVariable.readText(), trend)
  }
  return null
}

private fun fileIsOld(timestamp: String): Boolean {
  val stampSplit = timestamp.toString().split("T")
  val stampArr = stampSplit[1].split(":")
  val stampDateArr = stampSplit[0].toString().split("-")

  val currentDate = LocalDateTime.now().toString().split("T")
  val currentTimeArr = currentDate[1].split(":")
  val currentDateArr = currentDate[0].toString().split("-")

  return currentTimeArr[0].toInt() - stampArr[0].toInt() <= 3 ||
          currentDateArr[0].toInt() - stampDateArr[0].toInt() != 0
}