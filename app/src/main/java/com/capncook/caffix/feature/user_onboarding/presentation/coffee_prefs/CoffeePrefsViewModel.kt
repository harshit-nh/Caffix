package com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs

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
import kotlinx.coroutines.time.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class CoffeePrefsViewModel @Inject constructor(

): ViewModel() {


    private val _state = MutableStateFlow(CoffeePrefsState())
    val state: StateFlow<CoffeePrefsState> = _state.asStateFlow()

    init {
        loadDummyData()
    }


    private fun loadDummyData() {

        val coffeeOptions = listOf(
            CoffeeOptions("strong", "Strong", "Maximum intensity. Espresso shots or dark roasts.", R.drawable.ic_strong_type),
            CoffeeOptions("medium", "Medium", "The perfect balance. Everyday smoothness.", R.drawable.ic_medium_type),
            CoffeeOptions("mild", "Mild", "Gentle energy. Light roasts or milk-heavy blends.", R.drawable.ic_mild_type),
            CoffeeOptions("decaf", "Decaf", "All of the flavor, none of the jitter. Evening chill.", R.drawable.ic_decaf_type),
            CoffeeOptions("surprise", "Surprise Me", "Let our baristas choose based on your daily mood.", R.drawable.ic_surprise_type)
        )

        _state.update { it.copy(coffeeOptions = coffeeOptions) }
    }


    fun coffeeSelected(id: String) {
        _state.update{ it.copy(selectedCoffeeId = id, errorMessage = null) }
    }


    fun onNextClick() {

        if (_state.value.selectedCoffeeId == null) return


        viewModelScope.launch {

            _state.update { it.copy(isLoading = true) }

            delay(1000.milliseconds)

            _state.update {
                it.copy(
                    isLoading = false,
                    navigationAction = CoffeePrefsNavigationAction.NAVIGATE_TO_NEXT
                )
            }

        }

    }


    fun onSkipClick() {
        if (_state.value.isLoading) return
        _state.update { it.copy(navigationAction = CoffeePrefsNavigationAction.NAVIGATE_TO_NEXT) }
    }

    fun onNavigationConsumed() {
        _state.update { it.copy(navigationAction = null) }
    }

    fun clearError() {
        _state.update { it.copy(errorMessage = null) }
    }


}