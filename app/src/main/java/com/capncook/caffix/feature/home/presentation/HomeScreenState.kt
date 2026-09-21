package com.capncook.caffix.feature.home.presentation

import com.capncook.caffix.feature.home.domain.model.Category
import com.capncook.caffix.feature.home.domain.model.HeroBanner
import com.capncook.caffix.feature.home.domain.model.HomeSection

data class HomeScreenState(

    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,

    val location: String = "Locating...",

    val headerGradientColors: List<String> = listOf("#303030", "#1F1F1F", "#121212"),
    val searchBarColor: String? = "#2C2C2C",
    val locationTextColor: String? = "#FFFFFF",
    val heroBanner: HeroBanner? = null,

    val categories: List<Category> = emptyList(),
    val selectedCategorySlug: String? = null,

    val feedSections: List<HomeSection> = emptyList()

)
