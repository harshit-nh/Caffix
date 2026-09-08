package com.capncook.caffix.feature.auth.data.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.common.datastore.SessionManager
import com.capncook.caffix.feature.auth.data.remote.AuthApi
import com.capncook.caffix.feature.auth.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.auth.data.remote.dto.toDomain
import com.capncook.caffix.feature.auth.data.remote.dto.toLoginResendOtpDto
import com.capncook.caffix.feature.auth.data.remote.dto.toRegisterRequestDto
import com.capncook.caffix.feature.auth.data.remote.dto.toVerifyOtpDto
import com.capncook.caffix.feature.auth.domain.model.RegisterRequest
import com.capncook.caffix.feature.auth.domain.model.ApiResponse
import com.capncook.caffix.feature.auth.domain.model.AuthData
import com.capncook.caffix.feature.auth.domain.model.LoginResendOtpRequest
import com.capncook.caffix.feature.auth.domain.model.VerifyOtpRequest
import com.capncook.caffix.feature.auth.domain.repository.AuthRepository
import com.google.gson.Gson
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val gson: Gson,
    private val sessionManager: SessionManager
) : AuthRepository {


    override suspend fun registerUser(user: RegisterRequest): Resource<ApiResponse> {

        return try {
            val response = authApi.registerUser(user.toRegisterRequestDto())

            Resource.Success(ApiResponse(message = response.message))

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Registration failed",
                errorCode = parsedError?.errorCode
            )

        }catch (e: IOException){

            Resource.Error(message = "Couldn't reach the server. Check your internet connection")

        }catch (e: Exception){

            Resource.Error(message = "An unexpected error occurred")
        }

    }

    private fun parseErrorResponse(errorBodyJson: String?): ApiResponseDto<*>? {

        return try {
            if(!errorBodyJson.isNullOrBlank()){
                gson.fromJson(errorBodyJson, ApiResponseDto::class.java)
            }else {
                null
            }
        }catch (e: Exception){
            null
        }
    }



    override suspend fun verifyOtp(otpRequest: VerifyOtpRequest): Resource<AuthData> {

        return try {

            val response = authApi.verifyOtp(otpRequest.toVerifyOtpDto())

            val authDataDto = response.data
                ?: return Resource.Error<AuthData>(message = "Authentication failed from server")

            //Saving JWT Token
            sessionManager.saveToken(authDataDto.accessToken)
            sessionManager.saveOnboardingState(authDataDto.user.isOnboardingComplete)

            Resource.Success(authDataDto.toDomain())

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "OTP Verification failed",
                errorCode = parsedError?.errorCode
            )

        }catch (e: IOException) {

            Resource.Error(message = "Couldn't reach the server. Check your internet connection")

        }catch(e: Exception) {

            Resource.Error(message = "An unexpected error occurred")
        }
    }



    override suspend fun requestOtp(request: LoginResendOtpRequest): Resource<ApiResponse> {

        return try {
            val response = authApi.requestOtp(request.toLoginResendOtpDto())

            Resource.Success(
                ApiResponse(message = response.message)
            )

        }catch (e: HttpException) {

            val errorJson = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(errorJson)


            Resource.Error(
                message = parsedError?.message ?: "Failed to send OTP",
                errorCode = parsedError?.errorCode
            )

        }catch (e: IOException) {

            Resource.Error(message = "Couldn't reach the server. Check your internet connection.")

        } catch (e: Exception) {

            Resource.Error(message = "An unexpected error occurred.")
        }

    }


}