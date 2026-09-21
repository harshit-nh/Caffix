package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import androidx.core.graphics.toColorInt


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    containerColor: String? = null,
    isDarkTheme: Boolean = true
) {

    var searchText by remember { mutableStateOf("") }

    val resolvedContainerColor = remember(containerColor) {
        containerColor?.let { Color(it.toColorInt()) } ?: if (isDarkTheme) Color(0xFF1C1C1C) else Color(0xFFF5F5F5)
    }

    // Mathematically check if the background we just picked is light or dark
    // Luminance goes from 0.0 (pure black) to 1.0 (pure white)
    val isBackgroundLight = resolvedContainerColor.luminance() > 0.5f

    val textColor = if (isBackgroundLight) Color(0xFF121212) else Color.White
    val iconTint = if (isBackgroundLight) Color.DarkGray else Color.White
    val placeholderColor = if (isBackgroundLight) Color.Gray else Color.LightGray


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    "Find your comfort coffees",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    color = placeholderColor
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.regular_outline_search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = iconTint
                )
            },
            shape = RoundedCornerShape(
                topStart = 15.dp,
                topEnd = 15.dp,
                bottomEnd = 15.dp,
                bottomStart = 15.dp
            ),
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .height(57.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = resolvedContainerColor,
                focusedContainerColor = resolvedContainerColor, // Keep it consistent when typing
                cursorColor = CoffeeBrown,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )

        )

//        Spacer(modifier = Modifier.width(10.dp))
//
//        IconButton(
//            onClick = { },
//            modifier = Modifier
//                .size(width = 50.dp, height = 57.dp)
//                .background(
//                    color = CoffeeBrown,
//                    shape = RoundedCornerShape(15.dp)
//                )
//        ) {
//            Icon(
//                painter = painterResource(R.drawable.regular_outline_filter),
//                contentDescription = "Filter",
//                tint = Color.White,
//                modifier = Modifier.size(28.dp)
//            )
//        }
    }
}