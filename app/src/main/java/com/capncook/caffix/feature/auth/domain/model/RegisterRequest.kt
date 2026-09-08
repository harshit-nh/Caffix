package com.capncook.caffix.feature.auth.domain.model

data class RegisterRequest(
    val name: String,
    val phoneNumber: String,
    val email: String
)
