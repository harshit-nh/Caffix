package com.capncook.caffix.feature.home.data.remote.dto

import com.capncook.caffix.feature.home.domain.model.Category

data class CategoryDto(
    val id: String,
    val slug: String,
    val name: String,
    val description: String,
    val displayOrder: Int
)



fun List<CategoryDto>.toDomain(): List<Category> {

    val allCategory = Category(
        id = "All",
        slug = "All",
        name = "All",
        description = "List of all coffees and sections",
        displayOrder = 0
    )

    val categories = map {
        Category(
            id = it.id,
            slug = it.slug,
            name = it.name,
            description = it.description,
            displayOrder = it.displayOrder
        )
    }

    return listOf(allCategory) + categories
}
