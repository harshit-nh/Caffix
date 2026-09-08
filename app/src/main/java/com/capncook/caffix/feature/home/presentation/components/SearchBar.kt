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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = true
) {

    var searchText by remember { mutableStateOf("") }

    val containerColor = if (isDarkTheme) Color(0xFF2A2A2A) else Color(0xFFF5F5F5)
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val iconTint = if (isDarkTheme) Color.White else Color.DarkGray
    val placeholderColor = if (isDarkTheme) Color.Gray else Color.DarkGray


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = {
                Text(
                    "Find your coffees",
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
                unfocusedContainerColor = containerColor,
                focusedContainerColor = containerColor, // Keep it consistent when typing
                cursorColor = CoffeeBrown,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor
            )

        )

        Spacer(modifier = Modifier.width(10.dp))

        IconButton(
            onClick = { },
            modifier = Modifier
                .size(width = 50.dp, height = 57.dp)
                .background(
                    color = CoffeeBrown,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Icon(
                painter = painterResource(R.drawable.regular_outline_filter),
                contentDescription = "Filter",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}