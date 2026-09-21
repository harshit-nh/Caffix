package com.capncook.caffix.screens.detailscreen

import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.capncook.caffix.R
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun DetailsScreen(productId: Int, navController: NavController) {

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
//    val products = listOf(
//
//        Product(1, "Espresso", "Strong and Rich", 120.00, R.drawable.coffee_2),
//        Product(2, "Latte", "Smooth and Creamy", 160.00, R.drawable.coffee_3),
//        Product(3, "Cappuccino", "With chocolate", 128.00, R.drawable.coffee_1),
//        Product(4, "Mocha", "With cocoa flavor", 140.00, R.drawable.coffee_4),
//        Product(5, "Macchiato", "Bold and milky", 150.00, R.drawable.coffee_5),
//        Product(6, "Flat White", "Velvety smooth", 110.00, R.drawable.coffee_6),
//        Product(7, "Iced Mocha", "Refreshing and rich", 220.00, R.drawable.coffee_4)
//    )
//
//    val selectedProduct = products.find { it.id == productId }
//
//    if(selectedProduct == null) {
//
//        Text(text = "Product not found!", color = Color.Red,
//            fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold,
//            fontSize = 22.sp
//        )
//        return
//    }
//
//    Scaffold(
//        topBar = { DetailsScreenTopBar(navController) },
//        bottomBar = { DetailsScreenBottomBar(selectedProduct) }
//
//    ) { innerPadding ->
//
//        LazyColumn() {
//            item{
//                ProductDetailsContent(selectedProduct ,innerPadding)
//            }
//        }
//    }

}