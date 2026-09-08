package com.capncook.caffix.feature.auth.data.remote.dto

import com.capncook.caffix.feature.auth.domain.model.VerifyOtpRequest

data class VerifyOtpDto(
    val phoneNumber: String,
    val otpCode: String
)


fun VerifyOtpRequest.toVerifyOtpDto(): VerifyOtpDto {

    return VerifyOtpDto(
        phoneNumber = phoneNumber,
        otpCode = otpCode
    )
}



