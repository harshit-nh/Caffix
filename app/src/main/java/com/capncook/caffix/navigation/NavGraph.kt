package com.capncook.caffix.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.capncook.caffix.screens.cartscreen.CartScreen
import com.capncook.caffix.screens.detailscreen.DetailsScreen
import com.capncook.caffix.screens.favouritescreen.FavouriteScreen
import com.capncook.caffix.screens.homescreen.HomeScreen
import com.capncook.caffix.screens.profilescreen.ProfileScreen
import com.capncook.caffix.screens.welcomescreen.WelcomeScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.WelcomeScreen) {

        composable<Routes.WelcomeScreen> {
//            WelcomeScreen(navController)
        }

        composable<Routes.HomeScreen> {
            HomeScreen(
//                navController
            )
        }

        composable<Routes.DetailScreen> { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.DetailScreen>()
            DetailsScreen(productId = args.productId, navController)

        }

        composable<Routes.CartScreen> {
            CartScreen(navController)
        }

        composable<Routes.FavouriteScreen> {
            FavouriteScreen(navController)
        }

        composable<Routes.ProfileScreen> {
            ProfileScreen(navController)
        }
    }
}