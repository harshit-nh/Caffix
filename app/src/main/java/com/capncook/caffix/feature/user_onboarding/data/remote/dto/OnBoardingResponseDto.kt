package com.capncook.caffix.feature.user_onboarding.data.remote.dto

data class OnBoardingResponseDto<T>(
    val success: Boolean,
    val message: String,
    val data: T?,
    val errorCode: String?,
    val timestamp: String?
)
