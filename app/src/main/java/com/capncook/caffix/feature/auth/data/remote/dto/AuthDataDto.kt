package com.capncook.caffix.feature.auth.data.remote.dto

import com.capncook.caffix.feature.auth.domain.model.AuthData
import com.capncook.caffix.feature.auth.domain.model.User

data class AuthDataDto(
    val accessToken: String,
    val authType: String,
    val isNewUser: Boolean,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val role: String,
    val isVerified: Boolean,
    val isOnboardingComplete: Boolean,
    val profilePictureUrl: String?
)


fun AuthDataDto.toDomain() : AuthData {

    return AuthData(
        accessToken = accessToken,
        authType = authType,
        isNewUser = isNewUser,
        user = user.toDomain()
    )
}


fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        phoneNumber = phoneNumber,
        email = email,
        role = role,
        isVerified = isVerified,
        isOnboardingComplete = isOnboardingComplete,
        profilePictureUrl = profilePictureUrl
    )
}