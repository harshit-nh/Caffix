package com.capncook.caffix.feature.auth.presentation.registration

data class RegistrationState(

    val name: String = "",
    val phoneNumber: String = "",
    val email: String = "",

    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,

    val emailError: String? = null,
    val phoneError: String? = null,
    val generalError: String? = null
)
