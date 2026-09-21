package com.capncook.caffix.feature.home.domain.model

data class Product(
//    val id: Int,
//    val name: String,
//    val description: String,
//    val price: Double,
//    val imageRes: Int

    val id: String,
    val name: String,
    val tagline: String,
    val imageUrl: String,
    val basePrice: Double,
    val rating: Double,
    val reviewCount: Int,
    val badge: String,
    val temperature: String
)