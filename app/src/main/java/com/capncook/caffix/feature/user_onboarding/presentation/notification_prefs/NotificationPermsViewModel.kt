package com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


@HiltViewModel
class NotificationPermsViewModel @Inject constructor(

): ViewModel() {


    private val _state = MutableStateFlow(NotificationPermsState())
    val state: StateFlow<NotificationPermsState> = _state.asStateFlow()


    init {
        loadOptions()
    }


    private fun loadOptions() {

        val initialOptions = listOf(
            NotificationOptions("offers", "Offers & Discounts", "Promos, coupons and seasonal offers.", R.drawable.ic_tag),
            NotificationOptions("orders", "Order Updates", "Real-time status of your brew.", R.drawable.ic_delivery, requiresSystemPermission = true),
            NotificationOptions("recs", "Personalized Recommendations", "Roasts curated for your palate.", R.drawable.ic_recommendation),
            NotificationOptions("rewards", "Reward Points", "Stay updated on your loyalty balance.", R.drawable.premium_icon)
        )
        _state.update { it.copy(options = initialOptions) }
    }



    fun onToggleOption(id: String, isGranted: Boolean) {

        _state.update { currentState ->

            val updatedOptions = currentState.options.map { option ->
                if(option.id == id){
                    val willEnable= !option.isEnabled && isGranted
                    option.copy(isEnabled = willEnable)
                }else{
                    option
                }
            }
            currentState.copy(options = updatedOptions)
        }
    }


    fun syncSystemPermissionState(isGranted: Boolean) {

        _state.update { currentState ->

            val updatedOptions = currentState.options.map { option ->
                if(option.requiresSystemPermission) {
                    option.copy(isEnabled = isGranted)
                }else{
                    option
                }
            }

            currentState.copy(options = updatedOptions)
        }
    }


    fun onNextClick() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            delay(1000.milliseconds)

            _state.update {
                it.copy(
                    isLoading = false,
                    navigationAction = NotificationPermsNavigationAction.NAVIGATE_TO_NEXT
                )
            }
        }
    }


    fun onSkipClick() {
        if (_state.value.isLoading) return
        _state.update { it.copy(navigationAction = NotificationPermsNavigationAction.NAVIGATE_TO_NEXT) }
    }

    fun onNavigationConsumed() {
        _state.update { it.copy(navigationAction = null) }
    }



}