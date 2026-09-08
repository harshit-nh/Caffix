package com.capncook.caffix.feature.user_onboarding.di

import com.capncook.caffix.feature.user_onboarding.data.remote.OnboardingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnboardingNetworkModule {



    @Provides
    @Singleton
    fun provideOnboardingApi(retrofit: Retrofit): OnboardingApi {

        return retrofit.create(OnboardingApi::class.java)
    }
}