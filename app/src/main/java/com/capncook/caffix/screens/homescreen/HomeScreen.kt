package com.capncook.caffix.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.home.presentation.components.HomeScreenCategories
import com.capncook.caffix.feature.home.presentation.components.ProductsGrid
import com.capncook.caffix.feature.home.presentation.components.SearchBar


@Composable
fun HomeScreen(
//    navController: NavHostController
) {

//    val location = "Sahukara, Bara Bazaar, Bareilly, Uttar Pradesh"
//
//    Scaffold(
//        containerColor = Color.White,
//        bottomBar = {
////            MyBottomNavBar(navController, "Home")
//        }
//    ) { innerPadding ->
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(1f / 3f)
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = listOf(
//                            Color(0xFF303030),
//                            Color(0xFF1F1F1F),
//                            Color(0xFF121212)
//                        )
//                    )
//                )
//        )
//
//
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding(15.dp)
//            .padding(innerPadding)
//        ) {
//            Text(
//                text = "Location",
//                color = Color.Gray,
//                fontSize = 15.sp,
//                fontFamily = poppinsFontFamily,
//                fontWeight = FontWeight.Normal
//            )
//
//
//            Spacer(modifier = Modifier.height(4.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    modifier = Modifier.width(200.dp),
//                    text = location,
//                    color = Color.White,
//                    fontFamily = poppinsFontFamily,
//                    fontWeight = FontWeight.SemiBold,
//                    fontSize = 16.sp,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )
//
//                Icon(
//                    imageVector = Icons.Default.KeyboardArrowDown,
//                    contentDescription = "Change location",
//                    tint = Color.White
//                )
//
//
//            }
//
//            Spacer(modifier = Modifier.height(30.dp))
//
//            SearchBar()
//
//            Spacer(modifier = Modifier.height(40.dp))
//
//            LazyColumn {
//
//                item {
//
//
//                    Image(
//                        painter = painterResource(R.drawable.banner_1),
//                        contentDescription = "Banner Image"
//                    )
//
//                    Spacer(modifier = Modifier.height(15.dp))
//
//
////                    HomeScreenCategories()
//
//
//                    val product = listOf(
//
//                        Product(1, "Espresso", "Strong and Rich", 120.00, R.drawable.coffee_2),
//                        Product(2, "Latte", "Smooth and Creamy", 160.00, R.drawable.coffee_3),
//                        Product(3, "Cappuccino", "With chocolate", 128.00, R.drawable.coffee_1),
//                        Product(4, "Mocha", "With cocoa flavor", 140.00, R.drawable.coffee_4),
//                        Product(5, "Macchiato", "Bold and milky", 150.00, R.drawable.coffee_5),
//                        Product(6, "Flat White", "Velvety smooth", 110.00, R.drawable.coffee_6),
//                        Product(7, "Iced Mocha", "Refreshing and rich", 220.00, R.drawable.coffee_4)
//                    )
//
//
//                    ProductsGrid(products = product,
////                        navController = navController
//                    )
//                }
//            }
//
//
//        }
//    }
}