package com.capncook.caffix.feature.home.domain.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.home.domain.model.Category

interface HomeRepository {

    suspend fun getCategories(): Resource<List<Category>>
}