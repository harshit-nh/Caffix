package com.capncook.caffix.feature.auth.domain.model

data class VerifyOtpRequest(
    val phoneNumber: String,
    val otpCode: String
)

