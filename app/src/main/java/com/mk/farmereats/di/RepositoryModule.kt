package com.mk.farmereats.di

import com.mk.farmereats.data.repository.AuthRepositoryImp
import com.mk.farmereats.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(
        impl : AuthRepositoryImp
    ) : AuthRepository


}