package com.capncook.caffix.screens.cartscreen

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.capncook.caffix.R
import com.capncook.caffix.navigation_bottom.MyBottomNavBar
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun CartScreen(navController: NavHostController) {

//    val view = LocalView.current
//    val context = view.context as Activity
//
//    DisposableEffect(Unit) {
//        val window = context.window
//        val controller = WindowCompat.getInsetsController(window, view)
//
//        // Store the original state if you want to revert
//        val originalState = controller.isAppearanceLightStatusBars
//
//        //Force icons to black
//        controller.isAppearanceLightStatusBars = true
//
//        onDispose {
//            //Revert to what it was before when leaving the screen
//            controller.isAppearanceLightStatusBars = originalState
//        }
//    }
//
//    val location = "Sahukara, Bara Bazaar, Bareilly, Uttar Pradesh"
//
////    val cartItemProducts = remember {
////        listOf(
//////            Product(1, "Espresso", "Strong and Rich", 120.00, R.drawable.coffee_2),
//////            Product(2, "Latte", "Smooth and Creamy", 160.00, R.drawable.coffee_3),
//////            Product(3, "Cappuccino", "With chocolate", 128.00, R.drawable.coffee_1)
////        )
////    }
//
//    // Using a Map to track individual quantities for each product ID
//    val itemQuantities = remember {
//        mutableStateMapOf<Int, Int>().apply {
//            cartItemProducts.forEach { this[it.id] = 1 }
//        }
//    }
//
//    // Calculating Total Quantity and Price dynamically
//    val totalQuantity = itemQuantities.values.sum()
//    val amount = cartItemProducts.sumOf { (itemQuantities[it.id] ?: 0) * it.price.toInt() }
//    val deliveryFee = 25
//    val totalAmount =
//        if (amount > 500) {
//            amount
//        } else {
//            amount + deliveryFee
//        }
//
//    Scaffold(
//        topBar = { CartScreenTopBar() },
//        bottomBar = { MyBottomNavBar(navController, "Cart") },
//        containerColor = Color.White
//    ) { innerPadding ->
//
//        Column(modifier = Modifier
//            .padding(innerPadding)
//            .padding(horizontal = 16.dp)
//            .fillMaxSize()
//        ) {
//
//            // FIXED LOCATION SECTION
//            Column(modifier = Modifier.fillMaxWidth(),
//                verticalArrangement = Arrangement.Center
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        imageVector = Icons.Default.LocationOn,
//                        contentDescription = "Location",
//                        tint = Color.DarkGray,
//                        modifier = Modifier.size(22.dp)
//                    )
//                    Text(
//                        text = "Deliver to: ",
//                        color = Color.Black,
//                        fontSize = 15.sp,
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(
//                        modifier = Modifier.width(250.dp),
//                        text = location,
//                        color = Color.Blue.copy(alpha = 0.5f),
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 18.sp,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                    Icon(
//                        imageVector = Icons.Default.KeyboardArrowDown,
//                        contentDescription = "Change location",
//                        tint = Color.Black,
//                        modifier = Modifier.size(32.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//
//            // SCROLLABLE CONTENT SECTION
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//
//                items(cartItemProducts) { product ->
//                    CartItemCard(
//                        product = product,
//                        // Pass the individual quantity from the map
//                        quantity = itemQuantities[product.id] ?: 1,
//                        onQuantityChange = { newQuantity ->
//                            // Update only this specific product's quantity
//                            itemQuantities[product.id] = newQuantity
//                        }
//                    )
//                }
//
//                item {
//                    Spacer(modifier = Modifier.height(30.dp))
//
//                    Text(text = "Payment Summary",
//                        fontFamily = poppinsFontFamily,
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 20.sp,
//                        color = Color.Black
//                    )
//
//                    Spacer(modifier = Modifier.height(20.dp))
//
//
//                    Row(modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "Total Items ($totalQuantity)",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 16.sp,
//                            color = Color.DarkGray
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(5.dp))
//
//                    Row(modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "Price",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 16.sp,
//                            color = Color.DarkGray
//                        )
//                        Text(text = "₹$amount",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 16.sp,
//                            color = LightBrown
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(5.dp))
//
//                    Row(modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(text = "Delivery fee",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.Medium,
//                            fontSize = 16.sp,
//                            color = Color.DarkGray,
//                            style = TextStyle(
//                                textDecoration = if(amount > 500){
//                                    TextDecoration.LineThrough
//                                }else{
//                                    TextDecoration.None
//                                }
//                            )
//                        )
//                        Text(text = "₹$deliveryFee",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.SemiBold,
//                            fontSize = 16.sp,
//                            color = LightBrown,
//                            style = TextStyle(
//                                textDecoration = if(amount > 500){
//                                    TextDecoration.LineThrough
//                                }else{
//                                    TextDecoration.None
//                                }
//                            )
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(40.dp))
//
//                    PaymentModeSelectionCard(totalAmount)
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
//            }
//        }
//    }
}