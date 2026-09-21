    package com.capncook.caffix.feature.home.domain.repository

    import com.capncook.caffix.common.Resource
    import com.capncook.caffix.feature.home.domain.model.Category
    import com.capncook.caffix.feature.home.domain.model.HomeConfig
    import com.capncook.caffix.feature.home.domain.model.HomeSection

    interface HomeRepository {


        suspend fun getHomeConfig(): Resource<HomeConfig>

        suspend fun getCategories(): Resource<List<Category>>

        suspend fun getHomeFeed(categorySlug: String? = null): Resource<List<HomeSection>>
    }