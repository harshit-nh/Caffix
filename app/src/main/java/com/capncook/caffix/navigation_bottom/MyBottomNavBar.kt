package com.capncook.caffix.navigation_bottom

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.capncook.caffix.R
import com.capncook.caffix.navigation.Routes
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun MyBottomNavBar(navController: NavHostController, routes: String) {

    val navItems = listOf(
        NavItem("Home", R.drawable.regular_outline_home, Routes.HomeScreen),
        NavItem("Cart", R.drawable.regular_outline_bag, Routes.CartScreen),
        NavItem("Wishlist", R.drawable.regular_outline_heart, Routes.FavouriteScreen),
        NavItem("Profile", R.drawable.outline_account_circle_24, Routes.ProfileScreen)
    )

    NavigationBar(

        containerColor = Color.White,
        modifier = Modifier.height(80.dp)
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal) },
                modifier = Modifier.height(28.dp),
                onClick = {
                    //handling bottom bar navigation
                    navController.navigate(item.routes) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = item.title == routes,
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LightBrown,
                    selectedTextColor = LightBrown,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray,
                    indicatorColor = LightBrown.copy(alpha = 0.05f)
                )
            )
        }
    }
}

data class NavItem(
    var title: String,
    val icon: Int,
    val routes: Routes
)