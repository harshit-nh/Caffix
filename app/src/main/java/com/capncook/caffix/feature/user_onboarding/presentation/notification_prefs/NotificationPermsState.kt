package com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs


data class NotificationOptions(
    val id: String,
    val title: String,
    val description: String,
    val iconId: Int,
    val isEnabled: Boolean = false,
    val requiresSystemPermission: Boolean = false
)



data class NotificationPermsState(
    val options: List<NotificationOptions> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val navigationAction: NotificationPermsNavigationAction? = null
)


enum class NotificationPermsNavigationAction {
    NAVIGATE_TO_NEXT,
    NAVIGATE_TO_LOGIN
}
