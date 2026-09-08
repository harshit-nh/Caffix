package com.capncook.caffix.feature.user_onboarding.data.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.common.datastore.SessionManager
import com.capncook.caffix.feature.auth.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.user_onboarding.data.remote.OnboardingApi
import com.capncook.caffix.feature.user_onboarding.domain.model.ProfilePicture
import com.capncook.caffix.feature.user_onboarding.domain.repository.OnboardingRepository
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import java.net.URLConnection
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val onboardingApi: OnboardingApi,
    private val sessionManager: SessionManager,
    private val gson: Gson
): OnboardingRepository {



    override suspend fun uploadProfilePicture(imageFile: File): Resource<String> {

        return try {

            //Guess the exact MIME type
            val mimeType = URLConnection.guessContentTypeFromName(imageFile.name) ?: "image/jpeg"

            //Convert physical file into an OKHTTP RequestBody
            val requestFile = imageFile.asRequestBody(mimeType.toMediaTypeOrNull())


            val body = MultipartBody.Part.createFormData(
                "profilePicture",
                imageFile.name,
                requestFile
            )


            //Make API call
            val response = onboardingApi.uploadProfilePicture(body)


            //Extract URL
            val picUrl = response.data?.profilePicUrl
                ?: return Resource.Error("Failed to get image URL from server")


            Resource.Success(data = picUrl)

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Failed to upload image.",
                errorCode = parsedError?.errorCode
            )


        }catch (e: IOException) {
            Resource.Error(message = "Couldn't reach the server. Check your internet connection.")
        }catch (e: Exception) {
            Resource.Error(message = "An unexpected error occurred.")
        }

    }

    override suspend fun completeOnboarding(): Resource<Unit> {

        return try {
            val response = onboardingApi.completeOnboarding()

            sessionManager.saveOnboardingState(true)

            Resource.Success(Unit)

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Some error occurred.",
                errorCode = parsedError?.errorCode
            )

        }catch (e: IOException) {
            Resource.Error(message = "Couldn't reach the server. Check your internet connection.")
        }catch(e: Exception) {
            Resource.Error(message = "An unexpected error occurred.")
        }

    }


    override fun clearSession() {
        sessionManager.clearSession()
    }


    override suspend fun getUserProfile(): Resource<ProfilePicture> {

        return try {

            val response = onboardingApi.getUserProfile()

            Resource.Success(ProfilePicture(response.data?.profilePicUrl))

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.string()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Failed to fetch profile",
                errorCode = parsedError?.errorCode
            )

        }catch (e: okio.IOException){

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


}