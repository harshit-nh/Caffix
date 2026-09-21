package com.capncook.caffix.feature.home.data.remote.dto

import com.capncook.caffix.feature.home.domain.model.HomeSection
import com.capncook.caffix.feature.home.domain.model.Product

data class FeedDto(
    val sections: List<SectionDto>
)


data class SectionDto(
    val id: String,
    val title: String,
    val layoutType: String,
    val items: List<ProductItemDto>?,
    val data: PromoCardDataDto?
)


data class ProductItemDto(
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

data class PromoCardDataDto(
    val productId: String,
    val tag: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val actionText: String
)



fun FeedDto.toDomain(): List<HomeSection> {

    return sections.mapNotNull { sectionDto ->

        when(sectionDto.layoutType) {

            "HORIZONTAL_CAROUSEL" -> {
                HomeSection.Carousel(
                    id = sectionDto.id,
                    title = sectionDto.title,
                    items = sectionDto.items?.map { it.toDomain() } ?: emptyList()
                )
            }


            "TWO_COLUMN_GRID" -> {
                HomeSection.TwoColumnGrid(
                    id = sectionDto.id,
                    title = sectionDto.title,
                    items = sectionDto.items?.map { it.toDomain() } ?: emptyList()
                )
            }


            "PROMOTIONAL_CARD" -> {
                sectionDto.data?.let { promo ->
                    HomeSection.PromoCard(
                        id = sectionDto.id,
                        title = sectionDto.title,
                        productId = promo.productId,
                        tag = promo.tag,
                        promoTitle = promo.title,
                        description = promo.description,
                        imageUrl = promo.imageUrl,
                        actionText = promo.actionText
                    )
                }
            }

            else -> null
        }
    }
}



fun ProductItemDto.toDomain(): Product {

    return Product(
        id = id,
        name = name,
        tagline = tagline,
        imageUrl = imageUrl,
        basePrice = basePrice,
        rating = rating,
        reviewCount = reviewCount,
        badge = badge,
        temperature = temperature
    )
}
