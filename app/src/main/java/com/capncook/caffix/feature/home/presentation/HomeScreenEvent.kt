package com.capncook.caffix.feature.home.presentation

sealed class HomeScreenEvent {

    object OnFetchLocation: HomeScreenEvent()
    data class OnProductClicked(val productId: String): HomeScreenEvent()

    object OnSearchClicked: HomeScreenEvent()

    object OnFilterClicked: HomeScreenEvent()

    object OnLocationClicked: HomeScreenEvent()

    data class OnCategorySelected(val categorySlugName: String?): HomeScreenEvent()

    object OnRefresh: HomeScreenEvent()


}