package com.capncook.caffix.screens.profilescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun PreferencesCard(icon: ImageVector, title: String, description: String) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    var checked by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .graphicsLayer(
                scaleX = if (isPressed) 0.97f else 1f,
                scaleY = if (isPressed) 0.97f else 1f
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { }
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ){

        Row(modifier = Modifier.padding(15.dp).fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(modifier = Modifier
                .size(38.dp)
                .background(Color.LightGray.copy(alpha = 0.7f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ){

                Icon(
                    imageVector = icon,
                    contentDescription = description,
                    tint = Color.DarkGray.copy(alpha = 0.7f)

                )
            }


            Text(text = title,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 15.dp).weight(1f)
            )


            Switch(
                checked = checked,
                onCheckedChange = { checked = it },
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