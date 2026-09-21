package com.capncook.caffix.feature.home.di

import android.app.Application
import com.capncook.caffix.feature.home.data.location.LocationTrackerImpl
import com.capncook.caffix.feature.home.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocationModule {


    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class LocationBindModule {

        @Binds
        @Singleton
        abstract fun bindLocationTracker(
            locationTrackerImpl: LocationTrackerImpl
        ): LocationTracker

    }
}