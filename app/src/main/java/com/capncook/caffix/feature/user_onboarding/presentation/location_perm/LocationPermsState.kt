package com.capncook.caffix.feature.user_onboarding.presentation.location_perm

data class LocationPermsState(
    val navigationAction: LocationNavigationAction? = null
)


enum class LocationNavigationAction {
    NAVIGATE_TO_NEXT
}
