package com.capncook.caffix.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.auth.domain.model.LoginResendOtpRequest
import com.capncook.caffix.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()


    fun onPhoneChanged(phoneNumber: String) = _state.update { it.copy(phoneNumber = phoneNumber, phoneError = null) }



    fun requestOtp() {

        if(_state.value.phoneNumber.length != 10) {
            _state.update { it.copy(phoneError = "Phone number must be of 10 digits") }
            return
        }


        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    phoneError = null
                )
            }


            val request = LoginResendOtpRequest(
                phoneNumber = _state.value.phoneNumber
            )

            when(val result = authRepository.requestOtp(request)) {


                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is Resource.Error -> {

                    when(result.errorCode) {

                        "USER_NOT_FOUND" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    phoneError = "This number is not registered with us."
                                )
                            }
                        }

                        "VALIDATION_ERROR" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    phoneError = "Please enter a valid phone number."
                                )
                            }
                        }

                        else -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    phoneError = result.message
                                )
                            }
                        }
                    }
                }


                is Resource.Loading -> Unit
            }
        }
    }




}