package com.capncook.caffix.navigation

import kotlinx.serialization.Serializable



@Serializable data object SplashRoute



// --- 1. THE PARENT GRAPHS ---
@Serializable data object AuthGraph
@Serializable data object OnboardingGraph
@Serializable data object MainAppGraph

// --- 2. AUTH SCREENS ---
@Serializable data object WelcomeRoute
@Serializable data object RegistrationRoute
@Serializable data object LoginRoute
@Serializable data class OtpRoute(val phoneNumber: String, val isFromLogin: Boolean)

// --- 3. ONBOARDING SCREENS ---
@Serializable data object ProfileRoute
@Serializable data object CoffeeTypeRoute
@Serializable data object NotificationPermRoute
@Serializable data object LocationPermRoute
@Serializable data object OnBoardingCompleteRoute


// --- HOME SCREENS ---
@Serializable data object MainHomeRoute



// --- BOTTOM NAVIGATION TABS ---
@Serializable data object HomeTabRoute
@Serializable data object FavoritesTabRoute
@Serializable data object CartTabRoute
@Serializable data object ProfileTabRoute

// --- 5. DEEP SCREENS ---
@Serializable data class ProductDetailRoute(val productId: Int)



