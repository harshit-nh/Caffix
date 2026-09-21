package com.capncook.caffix.feature.home.data.remote.dto

import com.capncook.caffix.feature.home.domain.model.HeroBanner
import com.capncook.caffix.feature.home.domain.model.HomeConfig


data class HomeConfigDto(
    val theme: ThemeDto,
    val heroBanner: HeroBannerDto
)

data class ThemeDto(
    val headerGradientColors: List<String>,
    val searchBarBackgroundColor: String,
    val locationTextColor: String
)


data class HeroBannerDto(
    val badge: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val actionType: String,
    val actionPayload: String
)


fun HomeConfigDto.toDomain(): HomeConfig {

    return HomeConfig(
        headerGradientColors = theme.headerGradientColors,
        searchBarBackgroundColor = theme.searchBarBackgroundColor,
        locationTextColor = theme.locationTextColor,
        heroBanner = HeroBanner(
            badge = heroBanner.badge,
            title = heroBanner.title,
            subtitle = heroBanner.subtitle,
            imageUrl = heroBanner.imageUrl,
            actionType = heroBanner.actionType,
            actionPayload = heroBanner.actionPayload
        )
    )
}
