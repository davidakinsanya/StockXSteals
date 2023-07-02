package com.stockxsteals.app.view.compnents.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stockxsteals.app.utils.CustomText

@Composable
fun TermsAndConditions() {
  LazyColumn(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
    item {
      TopRow()
      Column(modifier = Modifier.fillMaxWidth(),
             horizontalAlignment = Alignment.CenterHorizontally) {
        CustomText().AppendCustomText(
          text = "**Privacy Policy for L8test.**\n" +
                  "**Effective Date: 18/05/2023**\n" +
                  "\n\n" +
                  "This Privacy Policy describes how L8test (\"we,\" \"us,\" or \"our\") collects, \n" +
                  "uses, shares, and protects the personal information of users (\"you\" or \"users\") \n" +
                  "of the L8test mobile application (\"the App\").\n" +
                  "\n\n" +
                  "**Information We Collect**\n" +
                  "We may collect the following types of information when you use the App:\n" +
                  "\n\n" +
                  "**1.1 Personal Information:**\n" +
                  "When you create an account, we may collect your name, email address, \n" +
                  "and any other information you choose to provide.\n" +
                  "\n\n" +
                  "**1.2 Usage Information:**\n" +
                  "We may collect information about how you interact with the App, such as the pages\n" +
                  "you visit, the features you use, and the actions you take.\n" +
                  "\n\n" +
                  "**1.3 Device Information**:\n" +
                  "We may collect information about your mobile device, including its unique device \n" +
                  "identifier, operating system, and other technical details.\n" +
                  "\n\n" +
                  "**Use of Information**\n" +
                  "\n\n" +
                  "**We may use the collected information for the following purposes:**\n" +
                  "\n\n" +
                  "**2.1 To Provide and Improve the App:**\n" +
                  "We use the information to operate, maintain, and improve the features and \n" +
                  "functionality of the App, as well as to personalize your experience.\n" +
                  "\n\n" +
                  "**2.2 Communications:**\n" +
                  "We may use your email address to send you updates, newsletters, and other \n" +
                  "promotional materials related to the App. You can opt-out of these communications\n" +
                  "at any time.\n" +
                  "\n\n" +
                  "**2.3 Analytics:**\n" +
                  "We may use analytics tools to analyze usage patterns, monitor trends, and gather \n" +
                  "statistical information to improve the App's performance and functionality.\n" +
                  "\n\n" +
                  "**Sharing of Information**\n" +
                  "\n\n" +
                  "We do not sell, trade, or rent your personal information to third parties. \n" +
                  "However, we may share information in the following circumstances:\n" +
                  "\n\n" +
                  "**3.1 Service Providers:**\n" +
                  "We may engage trusted third-party service providers to assist us in operating \n" +
                  "the App and delivering services. These providers have access to your information\n" +
                  "solely for the purpose of performing these tasks on our behalf and are obligated\n" +
                  "to protect your information.\n" +
                  "\n\n" +
                  "**3.2 Legal Compliance:**\n" +
                  "We may disclose your information if required by law, regulation, or legal process, \n" +
                  "or if we have a good faith belief that disclosure is necessary to protect our \n" +
                  "rights, detect or prevent fraud, or address security issues.\n" +
                  "\n" +
                  "**Data Security**\n" +
                  "We take reasonable measures to protect the security of your personal information\n" +
                  "and strive to ensure its confidentiality. However, please be aware that \n" +
                  "no method of transmission over the internet or electronic storage is 100% secure.\n" +
                  "\n\n" +
                  "**Children's Privacy**\n" +
                  "The App is not intended for use by individuals under the age of 13. We do not \n" +
                  "knowingly collect personal information from children. If you believe we have \n" +
                  "inadvertently collected information from a child, please contact us, \n" +
                  "and we will promptly delete it.\n" +
                  "\n\n" +
                  "**Changes to this Privacy Policy**\n" +
                  "We reserve the right to modify this Privacy Policy at any time. \n" +
                  "Any changes will be effective immediately upon posting. It is your responsibility\n" +
                  "to review this Privacy Policy periodically for updates.\n" +
                  "\n\n" +
                  "**Contact Us**\n" +
                  "If you have any questions, concerns, or suggestions regarding this Privacy Policy\n" +
                  "or our privacy practices, please contact us at l8test@googlegroups.com.",
          modifier = Modifier
            .padding(bottom = 40.dp)
            .width(450.dp),
          fontSize = 12.sp
        )
      }
    }
  }
}