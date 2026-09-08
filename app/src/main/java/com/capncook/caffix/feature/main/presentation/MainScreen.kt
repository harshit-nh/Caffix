package com.capncook.caffix.feature.main.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capncook.caffix.feature.home.presentation.components.HomeScreen
import com.capncook.caffix.feature.main.components.CaffixBottomBar
import com.capncook.caffix.navigation.CartTabRoute
import com.capncook.caffix.navigation.FavoritesTabRoute
import com.capncook.caffix.navigation.HomeTabRoute
import com.capncook.caffix.navigation.ProfileTabRoute


@Composable
fun MainScreen(
    // We pass the ROOT controller in case we need to navigate totally out of the bottom bar (e.g. Logout)
    rootNavController: NavHostController
) {

    // State to track if the bottom bar should be visible
    var isBottomBarVisible by remember { mutableStateOf(true) }

    // Scroll listener
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {

            // If scrolling down (negative Y), hide the bar. If scrolling up (positive Y), show it.
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if(available.y < -10) {
                    isBottomBarVisible = false
                } else if(available.y > 10) {
                    isBottomBarVisible = true
                }
                return Offset.Zero  // We don't consume the scroll, we just observe it
            }
        }
    }

    // This is the INNER controller. It only manages the 4 tabs.
    val bottomNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        bottomBar = {

            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                CaffixBottomBar(navController = bottomNavController)
            }

        }
    ) { innerPadding ->


        // We use bottom = 0.dp so the list goes all the way down,
        // preventing a white gap when the bottom bar hides.
        Box(modifier = Modifier.fillMaxSize()) {

            NavHost(
                navController = bottomNavController,
                startDestination = HomeTabRoute
            ) {

                composable<HomeTabRoute> {
                    HomeScreen(
                        onProductClick = {

                        }
                    )
                }

                composable<FavoritesTabRoute> {

                }

                composable<CartTabRoute> {

                }

                composable<ProfileTabRoute> {

                }
            }
        }
    }



}