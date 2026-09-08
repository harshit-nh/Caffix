package com.capncook.caffix.feature.home.data.remote

import com.capncook.caffix.feature.home.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.home.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface HomeApi {


    @GET("/api/categories")
    suspend fun getCategories(): ApiResponseDto<List<CategoryDto>>


}