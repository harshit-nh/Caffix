package com.capncook.caffix.feature.user_onboarding.presentation.location_perm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class LocationPermsViewModel @Inject constructor(

): ViewModel() {


    private val _state = MutableStateFlow(LocationPermsState())
    val state: StateFlow<LocationPermsState> = _state.asStateFlow()


    fun onPermissionGranted() {
        _state.update { it.copy(navigationAction = LocationNavigationAction.NAVIGATE_TO_NEXT) }
    }

    fun onNavigationConsumed() {
        _state.update { it.copy(navigationAction = null) }
    }


}