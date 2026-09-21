package com.capncook.caffix.screens.detailscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.IvoryWhite
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.ui_components.AppMessageDialog

@Composable
fun DetailsScreenBottomBar(product: Product) {

//    var showCartDialog by remember { mutableStateOf(false) }
//
//    BottomAppBar(
//        containerColor = Color.White,
//        modifier = Modifier.padding(bottom = 10.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(horizontal = 12.dp)
//        ) {
//            Column() {
//                Text(
//                    text = "Price",
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 18.sp,
//                    color = Color.DarkGray
//                )
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                Text(
//                    text = "₹${product.price}",
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 18.sp,
//                    color = LightBrown
//                )
//            }
//
//            Spacer(modifier = Modifier.width(45.dp))
//
//            Button(
//                modifier = Modifier.weight(1f)
//                    .height(56.dp),
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = LightBrown,
//                    contentColor = IvoryWhite
//                ),
//                onClick = {
//                    showCartDialog = true
//                }
//            ) {
//
//                Text(
//                    text = "Add to Cart",
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.Medium,
//                    fontSize = 20.sp
//                )
//            }
//
//            AppMessageDialog(
//                show = showCartDialog,
//                title = "Added to Cart",
//                message = "Your item has been added to your cart.",
//                onDismiss = { showCartDialog = false }
//            )
//        }
//    }

}