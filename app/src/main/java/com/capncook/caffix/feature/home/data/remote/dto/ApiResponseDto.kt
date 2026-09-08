package com.capncook.caffix.feature.home.data.remote.dto

data class ApiResponseDto<T>(
    val success: Boolean,
    val message: String,
    val data: T?,
    val errorCode: String?,
    val timestamp: String
)




