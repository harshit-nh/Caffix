package com.capncook.caffix.feature.auth.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.auth.domain.model.RegisterRequest
import com.capncook.caffix.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _state = MutableStateFlow(RegistrationState())
    val state: StateFlow<RegistrationState> = _state.asStateFlow()


    fun onNameChanged(name: String) = _state.update { it.copy(name = name) }
    fun onEmailChanged(email: String) = _state.update { it.copy(email = email, emailError = null) }
    fun onPhoneChanged(phone: String) = _state.update { it.copy(phoneNumber = phone, phoneError = null) }


    fun registerUser() {

        if (_state.value.phoneNumber.length != 10) {
            _state.update { it.copy(phoneError = "Phone number must of 10 digits") }
            return
        }

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    phoneError = null,
                    emailError = null,
                    generalError = null
                )
            }


            val request = RegisterRequest(
                name = _state.value.name,
                email = _state.value.email,
                phoneNumber = _state.value.phoneNumber
            )

            when (val result = authRepository.registerUser(request)) {

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                }

                is Resource.Error -> {

                    when (result.errorCode) {

                        "PHONE_ALREADY_REGISTERED" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    phoneError = result.message
                                )
                            }
                        }

                        "EMAIL_ALREADY_REGISTERED" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    emailError = result.message
                                )
                            }
                        }

                        "VALIDATION_ERROR" -> {
                            _state.update { it.copy(
                                isLoading = false,
                                generalError = result.message
                            )}
                        }

                        "INTERNAL_SERVER_ERROR" -> {
                            _state.update { it.copy(
                                isLoading = false,
                                generalError = "Servers are busy! Please try later."
                            ) }
                        }

                        else -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    generalError = result.message
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