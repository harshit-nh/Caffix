package com.capncook.caffix.feature.home.domain.model

data class Category(
    val id: String,
    val slug: String,
    val name: String,
    val description: String,
    val displayOrder: Int
)
