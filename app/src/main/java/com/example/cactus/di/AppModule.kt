package com.example.cactus.di

import android.content.Context
import com.example.cactus.BuildConfig
import com.example.cactus.api.PlantApi
import com.example.cactus.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providePlantApi(retrofit: Retrofit): PlantApi = retrofit.create(PlantApi::class.java)

    @Provides
    @Singleton
    fun providePlantDataStore(@ApplicationContext context: Context): PlantDataStore {
        return PlantRepository(context)
    }

    @Provides
    @Singleton
    fun provideUnsplashDataStore(plantApi: PlantApi): UnsplashDatastore {
        return UnsplashRepository(plantApi)
    }
}

object Constants {
    const val BASE_URL = "https://api.unsplash.com/"
}