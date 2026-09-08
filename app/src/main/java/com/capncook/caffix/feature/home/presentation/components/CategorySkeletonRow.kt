package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capncook.caffix.common.ui_components.skeleton.shimmerEffect

@Composable
fun CategorySkeletonRow() {

    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        repeat(4) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(34.dp)
                    .shimmerEffect()
            )
        }


    }
}



@Composable
fun ProductCarouselSkeleton() {

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        repeat(3) {
            item {
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(260.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}