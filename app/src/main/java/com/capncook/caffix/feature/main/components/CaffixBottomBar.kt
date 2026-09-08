package com.capncook.caffix.feature.main.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.navigation.CartTabRoute
import com.capncook.caffix.navigation.FavoritesTabRoute
import com.capncook.caffix.navigation.HomeTabRoute
import com.capncook.caffix.navigation.ProfileTabRoute


data class NavItem(
    val title: String,
    val icon: Int,
    val route: Any
)


@Composable
fun CaffixBottomBar(navController: NavController) {

    val navItems = listOf(
        NavItem("Home", R.drawable.regular_outline_home, HomeTabRoute),
        NavItem("Wishlist", R.drawable.regular_outline_heart, FavoritesTabRoute),
        NavItem("Cart", R.drawable.regular_outline_bag, CartTabRoute),
        NavItem("Profile", R.drawable.outline_account_circle_24, ProfileTabRoute)
    )


    // Observe the current backstack to know which tab is active
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    NavigationBar(
        modifier = Modifier
            .height(85.dp)
            .clip(RoundedCornerShape(topStart = 26.dp, topEnd = 26.dp)),
        containerColor = Color.White
    ) {


        navItems.forEach { item ->

            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(item.route::class)
            } == true


            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                    )
                },
                label = {
                    Text(item.title, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Medium)
                },
                modifier = Modifier.height(26.dp),
                selected = isSelected,
                alwaysShowLabel = false,
                onClick = {

                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
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