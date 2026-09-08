package com.capncook.caffix.feature.auth.domain.model

data class AuthData(
    val accessToken: String,
    val authType: String,
    val isNewUser: Boolean,
    val user: User
)

data class User(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val role: String,
    val isVerified: Boolean,
    val isOnboardingComplete: Boolean,
    val profilePictureUrl: String?
)
