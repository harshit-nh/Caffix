package com.capncook.caffix.feature.auth.presentation.otp_verification

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.auth.domain.model.LoginResendOtpRequest
import com.capncook.caffix.feature.auth.domain.model.VerifyOtpRequest
import com.capncook.caffix.feature.auth.domain.repository.AuthRepository
import com.capncook.caffix.navigation.OtpRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class OtpVerificationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val route = savedStateHandle.toRoute<OtpRoute>()
    val phoneNumber: String = route.phoneNumber
    val isFromLogin: Boolean = route.isFromLogin

    private val _state = MutableStateFlow(OtpVerificationState())
    val state: StateFlow<OtpVerificationState> = _state.asStateFlow()


    private var timerJob: Job? = null


    init {
        startTimer()
    }


    fun onOtpChange(otp: String) {
        if(otp.length <= 6){
            _state.update { it.copy(otpCode = otp, otpError = null) }
        }
    }


    fun verifyOtp() {

        if (_state.value.otpCode.length < 6) {
            _state.update { it.copy(otpError = "Please enter a valid 6-digit OTP") }
            return
        }


        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, otpError = null, generalError = null) }

            val request = VerifyOtpRequest(
                phoneNumber = phoneNumber,
                otpCode = _state.value.otpCode
            )


            when(val result = authRepository.verifyOtp(request)) {

                is Resource.Success -> {

                    val authData = result.data

                    val action = if(authData?.user?.isOnboardingComplete == true){
                        OtpNavigationAction.NAVIGATE_TO_HOME
                    } else {
                        OtpNavigationAction.NAVIGATE_TO_ONBOARDING
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            navigationAction = action
                        )
                    }
                }


                is Resource.Error -> {

                    when(result.errorCode) {

                        "OTP_INVALID" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    otpError = "Invalid code. Please try again."
                                )
                            }
                        }

                        "OTP_EXPIRED" -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    otpError = "This code has expired. Please request a new one."
                                )
                            }
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



    private fun startTimer() {
        timerJob?.cancel()  // Stop any existing timer

        _state.update { it.copy(timerValue = 20, isResendEnabled = false) }

        timerJob = viewModelScope.launch {
            for(i in 20 downTo 0) {
                _state.update { it.copy(timerValue = i) }

                if(i == 0){
                    _state.update { it.copy(isResendEnabled = true) }
                }else{
                    delay(1000L.milliseconds)
                }
            }
        }
    }



    fun resendOtp() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    generalError = null,
                    resendMessage = null,
                    otpError = null
                )
            }

            val request = LoginResendOtpRequest(phoneNumber = phoneNumber)

            when(val result = authRepository.requestOtp(request)) {

                is Resource.Success -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            resendMessage = "OTP resent successfully"
                        )
                    }

                    startTimer()
                }

                is Resource.Error -> {

                    _state.update {
                        it.copy(
                            isLoading = false,
                            generalError = result.message
                        )
                    }
                }

                is Resource.Loading -> Unit
            }
        }
    }



    fun clearResendMessage() {
        _state.update{ it.copy(resendMessage = null) }
    }


}