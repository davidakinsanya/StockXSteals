package com.stockxsteals.app.view.compnents.settings

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.stockxsteals.app.model.ui.allSocials
import com.stockxsteals.app.utils.WindowSize
import com.stockxsteals.app.viewmodel.ui.UIViewModel

@Composable
fun SocialSplashScreen(uiModel: UIViewModel,
                       windowSize: WindowSize
) {
  val listOfSocials = allSocials()
  val context = LocalContext.current
  LazyColumn(modifier =
  Modifier
    .fillMaxWidth()
    .fillMaxHeight(0.9f),
  ) {
    items(listOfSocials.size) { item ->
      Row(modifier =
      Modifier
        .fillMaxWidth()
        .padding(
          start = 45.dp,
          bottom = 50.dp,
          end = uiModel.settingEndPadding(windowSize))
        .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

        Text(text = listOfSocials[item].socials,
          fontWeight = FontWeight.SemiBold,
          fontSize = 18.sp,
          maxLines = 2,
          modifier = Modifier.width(150.dp)
        )

        Spacer(Modifier.padding(uiModel.settingSocialIconEndPadding(windowSize)))

        AsyncImage(
          model = listOfSocials[item].icon,
          contentDescription = "Social Media Logo",
          modifier =
          Modifier
            .fillMaxSize(.8f)
            .clickable {
              val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(listOfSocials[item].link)
              )
              val pendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(
                  0,
                  PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
              }
              pendingIntent.send()
            })
      }
    }
  }

}