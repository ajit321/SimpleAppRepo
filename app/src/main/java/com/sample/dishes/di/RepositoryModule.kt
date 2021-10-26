package com.sample.dishes.di

import com.sample.dishes.data.repository.HomeRepository
import com.sample.dishes.data.repository.interfaceImpl.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent ::class)
interface RepositoryModule {

    @Binds
    fun homeRepository(loginRepository: HomeRepository): HomeRepositoryImpl
}