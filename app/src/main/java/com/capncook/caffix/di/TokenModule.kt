package com.capncook.caffix.di

import com.capncook.caffix.common.datastore.SessionManager
import com.capncook.caffix.common.datastore.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class TokenModule {

    @Binds
    @Singleton
    abstract fun bindTokenManager(
        tokenManagerImpl: SessionManagerImpl
    ): SessionManager
}