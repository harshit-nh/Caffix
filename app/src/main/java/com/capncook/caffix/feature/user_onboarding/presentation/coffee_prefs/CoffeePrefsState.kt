package com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs

data class CoffeeOptions(
    val id: String,
    val title: String,
    val description: String,
    val iconId: Int
)


data class CoffeePrefsState(
    val coffeeOptions: List<CoffeeOptions> = emptyList(),
    val selectedCoffeeId: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigationAction: CoffeePrefsNavigationAction? = null
)


enum class CoffeePrefsNavigationAction {
    NAVIGATE_TO_NEXT,
    NAVIGATE_TO_LOGIN
}
