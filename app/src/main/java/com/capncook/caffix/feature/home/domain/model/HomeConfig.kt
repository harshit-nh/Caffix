package com.capncook.caffix.feature.home.domain.model

data class HomeConfig(
    val headerGradientColors: List<String>,
    val searchBarBackgroundColor: String,
    val locationTextColor: String,
    val heroBanner: HeroBanner
)


data class HeroBanner(
    val badge: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
    val actionType: String,
    val actionPayload: String
)
