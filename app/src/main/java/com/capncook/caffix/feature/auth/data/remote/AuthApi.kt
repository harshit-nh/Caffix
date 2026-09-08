package com.capncook.caffix.feature.auth.data.remote

import com.capncook.caffix.feature.auth.data.remote.dto.RegisterRequestDto
import com.capncook.caffix.feature.auth.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.auth.data.remote.dto.AuthDataDto
import com.capncook.caffix.feature.auth.data.remote.dto.LoginResendOtpRequestDto
import com.capncook.caffix.feature.auth.data.remote.dto.VerifyOtpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/auth/register")
    suspend fun registerUser(
        @Body registerRequest: RegisterRequestDto
    ): ApiResponseDto<Unit>


    @POST("/api/auth/verify-otp")
    suspend fun verifyOtp(
        @Body verifyOtpRequest: VerifyOtpDto
    ): ApiResponseDto<AuthDataDto>


    @POST("/api/auth/request-otp")
    suspend fun requestOtp(
        @Body loginResendOtpRequestDto: LoginResendOtpRequestDto
    ): ApiResponseDto<Unit>

}