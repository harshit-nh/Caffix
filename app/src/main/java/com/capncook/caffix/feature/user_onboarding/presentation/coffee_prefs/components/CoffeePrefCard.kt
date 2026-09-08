package com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun CoffeePrefCard(
    icon: Int,
    title: String,
    description: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val borderColor = if(isSelected) CoffeeBrown else Color.LightGray
    val borderWidth = if(isSelected) 1.5.dp else 1.dp
    val backgroundColor = if(isSelected) {
        colorResource(R.color.coffee_prefs_selected_bg)
    } else {
        Color.White
    }
    val iconTint = if(isSelected) CoffeeBrown else Color.DarkGray
    val iconBoxColor = Color(0xFFF3EBE1)


    val interactionSource = remember { MutableInteractionSource() }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(width = borderWidth, color = borderColor)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box(modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconBoxColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(32.dp),
                    tint = iconTint
                )
            }


            Spacer(Modifier.width(16.dp))


            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = title,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Text(
                    text = description,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 18.sp
                )
            }


        }
    }


}