package com.example.cactus.di

import android.content.Context
import com.example.cactus.repository.PlantDataStore
import com.example.cactus.repository.PlantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlantDataStore(@ApplicationContext context: Context): PlantDataStore {
        return PlantRepository(context)
    }
}
