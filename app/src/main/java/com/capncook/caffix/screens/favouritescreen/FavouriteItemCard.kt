package com.capncook.caffix.screens.favouritescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.LightGray
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun FavouriteItemCard(product: Product, onRemoveClick: () -> Unit) {

//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 25.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = LightGray
//        ),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 4.dp
//        )
//    ) {
//
//        Column(modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally) {
//
//
//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                Image(painter = painterResource(product.imageRes),
//                    contentDescription = "Coffee Image",
//                    modifier = Modifier
//                        .size(70.dp)
//                        .clip(RoundedCornerShape(10.dp))
//                )
//
//                Column(modifier = Modifier
//                    .weight(1f)
//                    .padding(start = 10.dp)) {
//
//                    Text(text = product.name,
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 20.sp,
//                        color = Color.Black
//                    )
//
//                    Text(text = product.description,
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.Normal,
//                        fontSize = 16.sp,
//                        color = Color.DarkGray
//                    )
//                }
//
//
//                Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ){
//
//                    IconButton(
//                        onClick = {
//                            onRemoveClick()
//                        },
//                        modifier = Modifier
//                            .background(
//                                color = LightBrown.copy(alpha = 0.1f),
//                                shape = CircleShape
//                            )
//                            .size(38.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Delete,
//                            contentDescription = "Remove",
//                            tint = Color.Red.copy(alpha = 0.7f)
//                        )
//                    }
//
//                }
//
//            }
//
//
//            Box(
//                modifier = Modifier
//                    .height(35.dp)
//                    .fillMaxWidth()
//                    .background(
//                        brush = Brush.linearGradient(
//                            colors = listOf(
//                                Color(0x0D303030),
//                                Color(0x0D1F1F1F)
//                            )
//                        )
//                    ),
//                contentAlignment = Alignment.Center
//            ){
//
//                TextButton(
//                    onClick = { },
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp)
//                        .height(35.dp)
//                    )
//                {
//
//                    Text(text = "Add to Cart",
//                        modifier = Modifier.fillMaxHeight(),
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 16.sp,
//                        color = LightBrown
//                    )
//
//                }
//            }
//
//        }
//
//
//    }

}