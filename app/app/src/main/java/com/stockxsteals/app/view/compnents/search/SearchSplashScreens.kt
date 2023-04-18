package com.stockxsteals.app.view.compnents.search

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.stockxsteals.app.model.SearchWithFilters
import com.stockxsteals.app.navigation.AppScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController) {

  val screens = listOf(
    AppScreens.SearchByCode.route,
    AppScreens.SearchBySlug.route
  )
  val buttons = listOf(
    "Country",
    "Currency",
    "Size",
    "Settings"
  )
  Scaffold() {
      Column(
        modifier = Modifier
          .padding(top = 30.dp)
      ) {

        LazyRow(modifier = Modifier
          .height(30.dp)
          .padding(start = 15.dp, end = 10.dp)
          .fillMaxWidth()) {

          items(buttons) { button ->
            if (button != "Settings") {
              Button(modifier = Modifier
                .width(90.dp)
                .padding(end = 5.dp),
                border = BorderStroke(0.5.dp, color = Color(224, 176, 255)),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                  backgroundColor = Color.White
                ),
                onClick = {
                  val searchWithFilters = SearchWithFilters("", "", "", "", 0.0)

                  if (button == "Country")  { } // TODO:
                    else if (button == "Currency") { } // TODO:
                      else { } // TODO:

                }) {

                Text(text = button, fontSize = 10.sp, fontWeight = FontWeight.Light)
              }

            } else {
              val focusManager = LocalFocusManager.current

              Row(modifier = Modifier.padding(start = 10.dp)) {
                IconButton(onClick = {
                  navController.navigate(navController.previousBackStackEntry?.destination?.route!!)
                  focusManager.clearFocus()
                }) {
                  Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Icon"
                  )
                }
                IconButton(onClick = { /*TODO*/ }) {
                  Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon"
                  )
                }
              }
            }
          }
        }
        DisplayExampleMessage(navController = navController)
      }
  }
}