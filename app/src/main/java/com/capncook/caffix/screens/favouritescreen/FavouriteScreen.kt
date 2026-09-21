package com.capncook.caffix.screens.favouritescreen

import android.app.Activity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.capncook.caffix.R
import com.capncook.caffix.navigation_bottom.MyBottomNavBar
import com.capncook.caffix.feature.home.domain.model.Product

@Composable
fun FavouriteScreen(navController: NavHostController) {

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
//
//    var favouriteItems by remember {
//        mutableStateOf(
//            listOf(
//                Product(1, "Espresso", "Strong and Rich", 120.00, R.drawable.coffee_2),
//                Product(2, "Latte", "Smooth and Creamy", 160.00, R.drawable.coffee_3),
//                Product(3, "Cappuccino", "With chocolate", 128.00, R.drawable.coffee_1)
//            )
//        )
//    }
//
//
//    Scaffold(
//        topBar = { FavouriteScreenTopBar() },
//        bottomBar = { MyBottomNavBar(navController, "Wishlist") },
//        containerColor = Color.White
//
//    ) { innerPadding ->
//
//        LazyColumn(
//            modifier = Modifier
//                .padding(16.dp)
//                .padding(innerPadding)
//        ) {
//
//            item {
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                favouriteItems.forEach { product ->
//
//                    FavouriteItemCard(
//                        product,
//                        onRemoveClick = {
//                            favouriteItems = favouriteItems - product
//                        }
//                    )
//                }
//            }
//        }
//    }

}