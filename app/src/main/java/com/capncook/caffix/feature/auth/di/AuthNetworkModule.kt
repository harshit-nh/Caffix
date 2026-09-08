package com.capncook.caffix.feature.auth.di

import com.capncook.caffix.feature.auth.data.remote.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthNetworkModule {


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {

        return retrofit.create(AuthApi::class.java)
    }

}