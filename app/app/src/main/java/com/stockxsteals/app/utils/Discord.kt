package com.stockxsteals.app.utils

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.stockxsteals.app.model.ui.SocialMedia

fun getDiscord(context: Context) {
  val intent = Intent(
    Intent.ACTION_VIEW,
    Uri.parse(SocialMedia.Discord.link)
  )
  val pendingIntent = TaskStackBuilder.create(context).run {
    addNextIntentWithParentStack(intent)
    getPendingIntent(
      0,
      PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
  }
  pendingIntent.send()
}