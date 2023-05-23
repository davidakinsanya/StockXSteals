package com.stockxsteals.app.utils

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class CustomText {
  private val boldRegex = Regex("(?<!\\*)\\*\\*(?!\\*).*?(?<!\\*)\\*\\*(?!\\*)")

  @Composable
  fun AppendCustomText(text: String, modifier: Modifier = Modifier) {

    var results: MatchResult? = boldRegex.find(text)
    val boldIndexes = mutableListOf<Pair<Int, Int>>()
    val keywords = mutableListOf<String>()
    var finalText = text

    while (results != null) {
      keywords.add(results.value)
      results = results.next()
    }

    keywords.forEach { keyword ->
      val indexOf = finalText.indexOf(keyword)
      val newKeyWord = keyword.removeSurrounding("**")
      finalText = finalText.replace(keyword, newKeyWord)
      boldIndexes.add(Pair(indexOf, indexOf + newKeyWord.length))
    }

    val annotatedString = buildAnnotatedString {
      append(finalText)

      // Add bold style to keywords that has to be bold
      boldIndexes.forEach {
        addStyle(
          style = SpanStyle(
            fontWeight = FontWeight.Bold,
            color = Color(0xff64B5F6),
            fontSize = 12.sp

          ),
          start = it.first,
          end = it.second
        )

      }
    }

    Text(
      text = annotatedString,
      modifier = modifier,
      fontSize = 16.sp,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Light
    )
  }
}