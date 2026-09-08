package com.capncook.caffix.feature.home.domain.model

sealed class HomeSection {
    data class PromoBanner(val imageRes: Int): HomeSection()
    data class CoffeeCarousel(val title: String, val products: List<Product>): HomeSection()
}