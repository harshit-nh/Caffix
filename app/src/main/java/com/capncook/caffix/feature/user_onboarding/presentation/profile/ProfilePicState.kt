package com.capncook.caffix.feature.user_onboarding.presentation.profile


data class ProfilePicState(
    val selectedImageUriString: String? = null,
    val remoteImageUrl: String? = null,
    val isFetchingProfile: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigationAction: ProfileNavigationAction? = null
)

enum class ProfileNavigationAction {
    NAVIGATE_TO_NEXT_FROM_UPLOAD, // Destroys backstack
    NAVIGATE_TO_NEXT_FROM_SKIP,   // Keeps backstack
    NAVIGATE_TO_LOGIN
}
