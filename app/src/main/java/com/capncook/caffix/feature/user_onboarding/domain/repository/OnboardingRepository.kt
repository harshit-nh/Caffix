package com.capncook.caffix.feature.user_onboarding.domain.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.user_onboarding.domain.model.ProfilePicture
import java.io.File

interface OnboardingRepository {

    suspend fun uploadProfilePicture(imageFile: File): Resource<String>

    suspend fun completeOnboarding() : Resource<Unit>

    suspend fun getUserProfile(): Resource<ProfilePicture>

    fun clearSession()

}