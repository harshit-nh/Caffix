    package com.capncook.caffix.feature.home.domain.repository

    import com.capncook.caffix.common.Resource
    import com.capncook.caffix.feature.home.domain.model.Category
    import com.capncook.caffix.feature.home.domain.model.HomeConfig

    interface HomeRepository {


        suspend fun getHomeConfig(): Resource<HomeConfig>
        suspend fun getCategories(): Resource<List<Category>>
    }