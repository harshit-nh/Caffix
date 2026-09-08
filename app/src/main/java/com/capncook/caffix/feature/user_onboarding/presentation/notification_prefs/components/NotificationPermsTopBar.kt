package com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationPrefsTopBar(
    currentStep: Int = 1,
    totalSteps: Int = 5
) {


    val progress = (currentStep.toFloat() / totalSteps.toFloat()).coerceIn(0f, 1f)

    val percentage = "${(progress * 100).toInt()}%"



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.account_bg))
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {


        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Step $currentStep/$totalSteps",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Text(
                text = percentage,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color(0xFFC67C4E)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = Color(0xFFC67C4E),
            trackColor = Color(0xFFEBD9CE),
            strokeCap = StrokeCap.Round
        )
    }
}
