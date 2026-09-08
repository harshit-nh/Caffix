package com.capncook.caffix.feature.user_onboarding.presentation.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.user_onboarding.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfilePicViewModel @Inject constructor(
    private val repository: OnboardingRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    companion object {
        private const val KEY_IMAGE_URI = "saved_image_uri"
    }


    private val _state = MutableStateFlow(ProfilePicState())
    val state: StateFlow<ProfilePicState> = _state.asStateFlow()


    init {
        val restoredUri = savedStateHandle.get<String>(KEY_IMAGE_URI)
        if (restoredUri != null) {
            _state.update { it.copy(selectedImageUriString = restoredUri) }
        }

        fetchUserProfile()
    }


    fun onImageSelected(uriString: String) {
        _state.update { it.copy(selectedImageUriString = uriString, errorMessage = null) }
        savedStateHandle[KEY_IMAGE_URI] = uriString
    }


    fun fetchUserProfile() {

        viewModelScope.launch {
            _state.update {
                it.copy(isFetchingProfile = true)
            }

            when (val result = repository.getUserProfile()) {

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            remoteImageUrl = result.data?.profilePicUrl,
                            isFetchingProfile = false
                        )
                    }
                }

                is Resource.Error -> {

                    when (result.errorCode) {

                        "TOKEN_EXPIRED" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "Session expired. Please login again.",
                                    navigationAction = ProfileNavigationAction.NAVIGATE_TO_LOGIN
                                )
                            }

                        }

                        else -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.message ?: "Failed to upload image."
                                )
                            }
                        }
                    }
                }

                is Resource.Loading -> Unit

            }
        }
    }


    fun onContinueClick(imageFile: File?) {

        if (imageFile == null) {

            if(_state.value.remoteImageUrl != null) {
                _state.update { it.copy(navigationAction = ProfileNavigationAction.NAVIGATE_TO_NEXT_FROM_UPLOAD) }
                return
            }else{
                _state.update { it.copy(errorMessage = "Please select an image first, or click Skip") }
                return
            }
        }


        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = repository.uploadProfilePicture(imageFile)) {

                is Resource.Success -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            navigationAction = ProfileNavigationAction.NAVIGATE_TO_NEXT_FROM_UPLOAD
                        )
                    }
                }

                is Resource.Error -> {

                    when (result.errorCode) {

                        "TOKEN_EXPIRED" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = "Session expired. Please login again.",
                                    navigationAction = ProfileNavigationAction.NAVIGATE_TO_LOGIN
                                )
                            }
                        }

                        else -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = result.message ?: "Failed to upload image."
                                )
                            }
                        }
                    }

                }


                is Resource.Loading -> Unit
            }
        }

    }


    fun onSkipClick() {
        _state.update { it.copy(navigationAction = ProfileNavigationAction.NAVIGATE_TO_NEXT_FROM_SKIP) }
    }


    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }


    fun onNavigationConsumed() {
        _state.update { it.copy(navigationAction = null) }
    }

}