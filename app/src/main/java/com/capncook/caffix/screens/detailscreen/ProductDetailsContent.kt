package com.capncook.caffix.screens.detailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.IvoryWhite
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun ProductDetailsContent(
    product: Product, innerPadding: PaddingValues
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .padding(innerPadding)
    ) {

        Image(
            painter = painterResource(product.imageRes),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop

        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(text = product.name,
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Ice / Hot",
                fontSize = 16.sp,
                color = Color.Gray,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )


            Icon(
                painter = painterResource(R.drawable.default_bean),
                contentDescription = "Bean",
                modifier = Modifier
                    .background(
                        color = IvoryWhite,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(36.dp)
                    .padding(6.dp)
                , tint = LightBrown
            )
        }


        Spacer(modifier = Modifier.height(25.dp))

        HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))

        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Description",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = product.description,
            fontSize = 16.sp,
            color = Color.Gray,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(30.dp))


        Text(text = "Size",
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold
        )


        Spacer(modifier = Modifier.height(12.dp))

        var selectedSizeText by remember { mutableStateOf("M") }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            listOf("S", "M", "L").forEach { size ->

                SelectSizeChip(
                    sizeText = size,
                    selected = selectedSizeText == size,
                    onClick = { selectedSizeText = size },
                    Modifier
                        .weight(1f)
                        .height(46.dp)
                )
            }
        }
    }

}