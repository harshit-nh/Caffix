package com.capncook.caffix.feature.auth.data.remote.dto

import com.capncook.caffix.feature.auth.domain.model.LoginResendOtpRequest

data class LoginResendOtpRequestDto(
    val phoneNumber: String
)



fun LoginResendOtpRequest.toLoginResendOtpDto(): LoginResendOtpRequestDto {

    return LoginResendOtpRequestDto(
        phoneNumber = phoneNumber
    )
}
