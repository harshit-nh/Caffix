package com.capncook.caffix.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capncook.caffix.R
import com.capncook.caffix.common.Resource
import com.capncook.caffix.common.datastore.SessionManager
import com.capncook.caffix.common.network.domain.ConnectivityObserver
import com.capncook.caffix.common.network.domain.GlobalRefreshManager
import com.capncook.caffix.feature.home.domain.location.LocationTracker
import com.capncook.caffix.feature.home.domain.model.Category
import com.capncook.caffix.feature.home.domain.model.HomeSection
import com.capncook.caffix.feature.home.domain.model.Product
import com.capncook.caffix.feature.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val sessionManager: SessionManager,
    private val locationTracker: LocationTracker,
    private val globalRefreshManager: GlobalRefreshManager,
    private val connectivityObserver: ConnectivityObserver
): ViewModel() {


    private val _state = MutableStateFlow(HomeScreenState(
        headerGradientColors = sessionManager.getThemeColors() ?: listOf("#303030", "#1F1F1F", "#121212"),
        searchBarColor = sessionManager.getSearchBarColor() ?: "#2C2C2C",
        locationTextColor = sessionManager.getLocationTextColor() ?: "#FFFFFF"
    ))

    val state: StateFlow<HomeScreenState> = _state.asStateFlow()


    init {
        loadInitialData()
        setUpRecoveryListeners()
    }


    private fun setUpRecoveryListeners() {

        //Manual try again button
        viewModelScope.launch {
            globalRefreshManager.refreshEvent.collect {
                if(!_state.value.isLoading) {
                    loadInitialData()
                }
            }
        }


        viewModelScope.launch {
            connectivityObserver.observe().collectLatest { status ->

                if(status == ConnectivityObserver.Status.Available) {

                    val needsRecovery = _state.value.error != null || _state.value.feedSections.isEmpty()
                    if(needsRecovery && !_state.value.isLoading) {
                        loadInitialData()
                    }
                }
            }
        }
    }



    fun onEvent(event: HomeScreenEvent) {

        when(event) {

            is HomeScreenEvent.OnFetchLocation -> {
                fetchUserLocation()
            }

            is HomeScreenEvent.OnCategorySelected -> {
                selectCategory(event.categorySlugName)
            }

            is HomeScreenEvent.OnProductClicked -> {

            }

            is HomeScreenEvent.OnRefresh -> {
                refreshHomeData()
            }

            else -> {

            }
        }
    }


    private fun fetchUserLocation() {

        viewModelScope.launch {

            _state.update { it.copy(location = "Locating...") }

            when(val result = locationTracker.getCurrentAddress()) {

                is Resource.Success -> {
                    _state.update { it.copy(location = result.data ?: "Select Delivery location") }
                }

                is Resource.Error -> {
                    _state.update { it.copy(location = result.message ?: "Select Delivery Location") }
                }

                is Resource.Loading -> Unit
            }
        }
    }



    private fun loadInitialData() {

        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, error = null) }

            val configDeferred = async { repository.getHomeConfig() }
            val categoriesDeferred = async { repository.getCategories() }
            val feedDeferred = async { repository.getHomeFeed(categorySlug = null) }

            val configResult = configDeferred.await()
            val categoryResult = categoriesDeferred.await()
            val feedResult = feedDeferred.await()


            if(configResult is Resource.Success) {

                configResult.data?.let { config ->

                    sessionManager.saveThemeColors(
                        colors = config.headerGradientColors,
                        searchBarColor = config.searchBarBackgroundColor,
                        locationTextColor = config.locationTextColor
                    )

                    _state.update { state ->
                        state.copy(
                            headerGradientColors = config.headerGradientColors,
                            searchBarColor = config.searchBarBackgroundColor,
                            locationTextColor = config.locationTextColor,
                            heroBanner = config.heroBanner
                        )
                    }
                }
            } else if(configResult is Resource.Error) {
                _state.update { it.copy(error = configResult.message) }
            }


            if(categoryResult is Resource.Success) {

                val categories = categoryResult.data ?: emptyList()
                _state.update { state ->
                    state.copy(
                        categories = categories,
                        selectedCategorySlug = categories.firstOrNull()?.slug
                    )
                }

            }else if(categoryResult is Resource.Error) {
                _state.update { it.copy(error = categoryResult.message) }
            }


            if(feedResult is Resource.Success) {

                _state.update { state ->
                    state.copy(feedSections = feedResult.data ?: emptyList())
                }

            }else if(feedResult is Resource.Error){
                _state.update { it.copy(error = feedResult.message) }
            }

            _state.update { it.copy(isLoading = false, isRefreshing = false) }

        }
    }


    private fun selectCategory(categorySlugName: String?) {

        if(_state.value.selectedCategorySlug == categorySlugName) return

        viewModelScope.launch {

            _state.update{
                it.copy(selectedCategorySlug = categorySlugName, isLoading = true)
            }


            when(val feedResult = repository.getHomeFeed(categorySlugName)) {

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            feedSections = feedResult.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = feedResult.message
                        )
                    }
                }

                is Resource.Loading -> Unit
            }

        }

    }


    private fun refreshHomeData() {

        _state.update { it.copy(isRefreshing = true) }
        loadInitialData()
    }





}