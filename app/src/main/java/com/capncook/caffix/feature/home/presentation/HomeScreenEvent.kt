package com.capncook.caffix.feature.home.presentation

sealed class HomeScreenEvent {

    data class OnProductClicked(val productId: Int): HomeScreenEvent()

    object OnSearchClicked: HomeScreenEvent()

    object OnFilterClicked: HomeScreenEvent()

    object OnLocationClicked: HomeScreenEvent()

    data class OnCategorySelected(val categoryId: String?): HomeScreenEvent()

    object OnRefresh: HomeScreenEvent()


}