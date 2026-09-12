package com.capncook.caffix.feature.home.data.repository

import com.capncook.caffix.common.Resource
import com.capncook.caffix.feature.auth.data.remote.dto.ApiResponseDto
import com.capncook.caffix.feature.home.data.remote.HomeApi
import com.capncook.caffix.feature.home.data.remote.dto.toDomain
import com.capncook.caffix.feature.home.domain.model.Category
import com.capncook.caffix.feature.home.domain.model.HomeConfig
import com.capncook.caffix.feature.home.domain.repository.HomeRepository
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi,
    private val gson: Gson
): HomeRepository {


    override suspend fun getHomeConfig(): Resource<HomeConfig> {


        return try {

            val response = api.getHomeConfig()
            val homeConfig = response.data!!.toDomain()

            Resource.Success(homeConfig)

        }catch (e: HttpException) {

            val error = e.response()?.errorBody()?.toString()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Some error occurred",
                errorCode = parsedError?.errorCode
            )

        }catch (e: okio.IOException) {

            Resource.Error(message = "Couldn't reach the server. Check your internet connection")

        }catch (e: Exception) {

            Resource.Error(message = "An unexpected error occurred")
        }
    }


    override suspend fun getCategories(): Resource<List<Category>> {

        return try {

            val response = api.getCategories()
            val categories = response.data!!.toDomain()

            Resource.Success(categories)

        } catch (e: HttpException) {

            val error = e.response()?.errorBody()?.toString()
            val parsedError = parseErrorResponse(error)

            Resource.Error(
                message = parsedError?.message ?: "Some error occurred",
                errorCode = parsedError?.errorCode
            )

        }catch (e: IOException) {

            Resource.Error(message = "Couldn't reach the server. Check your internet connection")

        }catch (e: Exception) {

            Resource.Error(message = "An unexpected error occurred")
        }
    }



    private fun parseErrorResponse(errorBodyJson: String?): ApiResponseDto<*>? {

        return try {
            if(!errorBodyJson.isNullOrBlank()){
                gson.fromJson(errorBodyJson, ApiResponseDto::class.java)
            }else {
                null
            }
        }catch (e: Exception){
            null
        }
    }



}