package com.capncook.caffix.screens.cartscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun CartItemCard(
    product: Product, 
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGray
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
//            Image(painter = painterResource(product.imageRes),
//                contentDescription = "Coffee Image",
//                modifier = Modifier
//                    .size(70.dp)
//                    .clip(RoundedCornerShape(10.dp))
//            )

            Column(modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)) {

                Text(text = product.name,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

//                Text(text = product.description,
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.Normal,
//                    fontSize = 16.sp,
//                    color = Color.DarkGray
//                )
            }
            
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    IconButton(
                        onClick = {
                            if (quantity > 1) onQuantityChange(quantity - 1)
                        },
                        modifier = Modifier.background(
                            color = LightBrown.copy(alpha = 0.1f),
                            shape = CircleShape
                        ).size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease",
                            tint = LightBrown
                        )
                    }

                    Text(
                        text = quantity.toString(), fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )

                    IconButton(
                        onClick = { onQuantityChange(quantity + 1) },
                        modifier = Modifier.background(
                            color = LightBrown.copy(alpha = 0.1f),
                            shape = CircleShape
                        ).size(25.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            tint = LightBrown
                        )
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))
                
//                Text(text = "₹${(quantity * product.price).toInt()}",
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 18.sp,
//                    color = LightBrown
//                )
            }
        }
    }
}