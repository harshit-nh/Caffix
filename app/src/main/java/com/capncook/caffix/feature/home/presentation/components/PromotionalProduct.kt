package com.capncook.caffix.feature.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.capncook.caffix.R
import com.capncook.caffix.common.Constants
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.home.domain.model.HomeSection


@Composable
fun PromotionalProduct(
    promo: HomeSection.PromoCard,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(360.dp)
            .clickable {
                onClick(promo.productId)
            },
        shape = RoundedCornerShape(14.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${Constants.BASE_URL}${promo.imageUrl}")
                    .crossfade(true)
                    .memoryCacheKey("${Constants.BASE_URL}${promo.imageUrl}")
                    .diskCacheKey("${Constants.BASE_URL}${promo.imageUrl}")
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.9f)
                            ),
                            startY = 300f  // Starts the gradient slightly further down
                        )
                    )
            )


            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {

                Text(
                    text = promo.tag,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily
                )


                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = promo.title,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily
                )


                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = promo.description,
                    color = Color.White.copy(alpha = 0.8f),
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                    lineHeight = 22.sp,
                    fontSize = 16.sp
                )


                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = promo.actionText,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Try Now",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )

                }

            }
        }

    }

}