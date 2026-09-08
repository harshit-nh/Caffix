package com.capncook.caffix.feature.user_onboarding.data.remote

import com.capncook.caffix.feature.user_onboarding.data.remote.dto.OnBoardingResponseDto
import com.capncook.caffix.feature.user_onboarding.data.remote.dto.ProfilePictureDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface OnboardingApi {


    @Multipart
    @POST("/api/user/profile-picture")
    suspend fun uploadProfilePicture(
        @Part profilePicture: MultipartBody.Part
    ): OnBoardingResponseDto<ProfilePictureDto>


    @POST("/api/user/complete-onboarding")
    suspend fun completeOnboarding(): OnBoardingResponseDto<Unit>


    @GET("/api/user/me")
    suspend fun getUserProfile(): OnBoardingResponseDto<ProfilePictureDto>



}