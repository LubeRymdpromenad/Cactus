package com.example.cactus.repository

import com.example.cactus.api.PlantApi
import com.example.cactus.data.UnsplashData
import com.example.cactus.data.mapper.UnsplashResponseMapper

private val mapper = UnsplashResponseMapper()

class UnsplashRepository(private val plantApi: PlantApi) : UnsplashDatastore {
    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<UnsplashData> {
        return plantApi.searchPhotos(query, page, perPage).run {
            mapper.map(this)
        }
    }
}
