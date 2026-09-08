package com.capncook.caffix.feature.user_onboarding.data.remote.dto

import com.capncook.caffix.feature.user_onboarding.domain.model.ProfilePicture

data class ProfilePictureDto(
    val profilePicUrl: String?
)



fun ProfilePictureDto.toProfilePicture(): ProfilePicture {

    return ProfilePicture(
        profilePicUrl = profilePicUrl
    )
}
