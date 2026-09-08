package com.capncook.caffix.feature.home.presentation

import com.capncook.caffix.feature.home.domain.model.Category
import com.capncook.caffix.feature.home.domain.model.HomeSection

data class HomeScreenState(

    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,

    val location: String = "Locating...",

    val categories: List<Category> = emptyList(),
    val selectedCategoryId: String? = null,

    val feedSections: List<HomeSection> = emptyList()

)
