package com.capncook.caffix.feature.auth.domain.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.auth.domain.model.RegisterRequest
import com.capncook.caffix.feature.auth.domain.model.ApiResponse
import com.capncook.caffix.feature.auth.domain.model.AuthData
import com.capncook.caffix.feature.auth.domain.model.LoginResendOtpRequest
import com.capncook.caffix.feature.auth.domain.model.VerifyOtpRequest

interface AuthRepository {

    suspend fun registerUser(user: RegisterRequest): Resource<ApiResponse>

    suspend fun requestOtp(request: LoginResendOtpRequest): Resource<ApiResponse>

    suspend fun verifyOtp(otpRequest: VerifyOtpRequest): Resource<AuthData>

}