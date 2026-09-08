package com.capncook.caffix.feature.user_onboarding.presentation.final_screen

data class FinalScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigationAction: FinalScreenNavigationAction? = null
)



enum class FinalScreenNavigationAction {
    NAVIGATE_TO_HOME,
    NAVIGATE_TO_LOGIN
}
