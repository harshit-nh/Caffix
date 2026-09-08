package com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun NotificationPrefsCard(
    icon: Int,
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                   onCheckedChange(!checked)
                }
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        border = if(checked) BorderStroke(1.dp, CoffeeBrown.copy(alpha = 0.5f)) else null
    ){

        Row(
            modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF3EBE1), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(28.dp),
                    tint = CoffeeBrown
                )
            }



            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {

                Text(text = title,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 18.sp
                )

                Text(text = description,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )

            }



            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                colors = SwitchDefaults.colors(
                    // Colors when the switch is ON
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFFC67C4E),
                    checkedBorderColor = Color(0xFFC67C4E),

                    // Colors when the switch is OFF
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color(0xFFF2E4DC),
                    uncheckedBorderColor = Color(0xFFDBC1B2)
                )
            )


        }
    }

}