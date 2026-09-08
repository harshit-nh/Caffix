package com.capncook.caffix.feature.auth.presentation.otp_verification

data class OtpVerificationState(
    val otpCode: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val otpError: String? = "",
    val generalError: String? = "",

    val timerValue: Int = 20,
    val isResendEnabled: Boolean = false,
    val resendMessage: String? = null,
    val navigationAction: OtpNavigationAction? = null
)




enum class OtpNavigationAction {
    NAVIGATE_TO_HOME,
    NAVIGATE_TO_ONBOARDING
}
