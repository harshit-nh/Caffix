package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.LightGray
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.home.domain.model.Category

@Composable
fun HomeScreenCategories(
    categories: List<Category>,
    selectedCategoryId: String?,
    onCategoryClick: (String) -> Unit
) {

    LazyRow(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categories) { category ->

            val isSelected = category.id == selectedCategoryId

            Box(
                modifier = Modifier
                    .height(35.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
                    .clickable {
                        onCategoryClick(category.id)
                    }
                    .background(
                        color = if (isSelected) LightBrown
                        else Color.White.copy(alpha = 0.8f)
                    ).padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = category.name,
                    fontSize = 14.sp,
                    color = if (isSelected) Color.White else Color.Black,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1
                )
            }
        }
    }

}