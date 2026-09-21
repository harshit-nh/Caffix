package com.capncook.caffix.feature.home.data.remote

import com.capncook.caffix.feature.home.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.home.data.remote.dto.CategoryDto
import com.capncook.caffix.feature.home.data.remote.dto.FeedDto
import com.capncook.caffix.feature.home.data.remote.dto.HomeConfigDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {


    @GET("/api/categories")
    suspend fun getCategories(): ApiResponseDto<List<CategoryDto>>

    @GET("/api/catalog/home-config")
    suspend fun getHomeConfig(): ApiResponseDto<HomeConfigDto>

    @GET("/api/catalog/feed")
    suspend fun getHomeFeed(
        @Query("category") categoryId: String? = null
    ): ApiResponseDto<FeedDto>


}