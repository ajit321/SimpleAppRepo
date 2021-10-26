package com.sample.dishes.di

import android.app.Application
import com.sample.dishes.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideNotesDao(database: AppDatabase) = database.getSimpleDao()
}