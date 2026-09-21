package com.capncook.caffix.feature.home.domain.model

sealed class HomeSection {


    data class Carousel(
        val id: String,
        val title: String,
        val items: List<Product>
    ): HomeSection()

    data class TwoColumnGrid(
        val id: String,
        val title: String,
        val items: List<Product>
    ): HomeSection()

    data class PromoCard(
        val id: String,
        val title: String,
        val productId: String,
        val tag: String,
        val promoTitle: String,
        val description: String,
        val imageUrl: String,
        val actionText: String
    ): HomeSection()

}