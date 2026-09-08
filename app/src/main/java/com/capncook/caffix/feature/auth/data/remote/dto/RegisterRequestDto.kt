package com.capncook.caffix.feature.auth.data.remote.dto

import com.capncook.caffix.feature.auth.domain.model.RegisterRequest

data class RegisterRequestDto(
    val name: String,
    val phoneNumber: String,
    val email: String
)


fun RegisterRequest.toRegisterRequestDto(): RegisterRequestDto {

    return RegisterRequestDto(
        name = name,
        phoneNumber = phoneNumber,
        email = email,
    )
}

