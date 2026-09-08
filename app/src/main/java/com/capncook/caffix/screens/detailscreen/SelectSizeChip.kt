package com.capncook.caffix.screens.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.CharcoalGray
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun SelectSizeChip(sizeText: String, selected: Boolean, onClick: () -> Unit, modifier: Modifier) {

    Box(
        modifier = modifier
            .background(
                color = if(selected) Color(0xFFF7F0EB) else Color(0xFFFDFDFD),
                    RoundedCornerShape(12.dp)
            )
            .border(
                1.dp,
                color = if(selected) Color(0xFFC67C4E) else Color(0xFFE1E1E1),
                RoundedCornerShape(12.dp)
            )
            .height(46.dp)
            .clickable{ onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = sizeText,
            fontSize = 16.sp,
            color = if(selected) LightBrown else CharcoalGray,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
    }


}