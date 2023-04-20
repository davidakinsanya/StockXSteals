package com.stockxsteals.app.view.compnents.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stockxsteals.app.utils.CustomText

@Composable
fun ExampleInput(type: String) {
  Column(modifier = Modifier.padding(start = 15.dp, top = 5.dp)) {
    when (type) {
      "Code" -> {
        CustomText().AppendCustomText(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 50.dp),
          text = "Example of a valid code is: **'CT5053-001'**.\n(Nike SB Dunk Low Travis Scott)",
        )
      }
      "Slug" -> {
        CustomText().AppendCustomText(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end  = 50.dp),
          text = "Example of a valid slug is: \n **'nike-air-max-1-travis-scott-wheat'**. \n (Nike SB Dunk Low Travis Scott)",
        )
      }
    }
  }
}

@Composable
fun DisplayExampleMessage(selected: MutableState<String>) {
  when (selected.value) {
    "Code" -> {
      ExampleInput(type = "Code")
    }
    "Slug" -> {
      ExampleInput(type = "Slug")
    }
  }
}