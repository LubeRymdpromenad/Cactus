package com.example.cactus.api

import com.example.cactus.BuildConfig
import com.example.cactus.api.responses.PlantsResponse
import com.example.cactus.api.responses.UnsplashSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PAGE = 1
private const val DEFAULT_PER_PAGE = 20

interface PlantApi {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): UnsplashSearchResponse

    @GET("search/photos")
    suspend fun getPlants(
        @Query("query") query: String,
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("per_page") perPage: Int = DEFAULT_PER_PAGE,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): PlantsResponse
}
