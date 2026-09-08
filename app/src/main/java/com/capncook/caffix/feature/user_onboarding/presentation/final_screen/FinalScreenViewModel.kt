package com.capncook.caffix.feature.user_onboarding.presentation.final_screen

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
import javax.inject.Inject


@HiltViewModel
class FinalScreenViewModel @Inject constructor(
    private val repository: OnboardingRepository
): ViewModel() {


    private val _state = MutableStateFlow(FinalScreenState())
    val state: StateFlow<FinalScreenState> = _state.asStateFlow()



    fun completeOnBoarding() {

        if(_state.value.isLoading) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null
                )
            }


            when(val result = repository.completeOnboarding()) {

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            navigationAction = FinalScreenNavigationAction.NAVIGATE_TO_HOME
                        )
                    }
                }


                is Resource.Error -> {

                    if(result.errorCode == "TOKEN_EXPIRED") {
                        repository.clearSession()

                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = "Session expired. Please login again.",
                                navigationAction = FinalScreenNavigationAction.NAVIGATE_TO_LOGIN
                            )
                        }

                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.message ?: "Some error occurred."
                            )
                        }
                    }
                }

                is Resource.Loading -> Unit
            }
        }
    }


    fun onNavigationConsumed() {
        _state.update {
            it.copy(navigationAction = null)
        }
    }

    fun clearError() {
        _state.update {
            it.copy(errorMessage = null)
        }
    }

}