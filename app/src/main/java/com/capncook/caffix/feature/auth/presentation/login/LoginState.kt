package com.capncook.caffix.feature.auth.presentation.login

data class LoginState(

    val phoneNumber: String = "",
    val otpCode: String = "",

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,

    val phoneError: String? = null
)
