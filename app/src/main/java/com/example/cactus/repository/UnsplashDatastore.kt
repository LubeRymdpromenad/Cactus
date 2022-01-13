package com.example.cactus.repository

import com.example.cactus.data.UnsplashData

interface UnsplashDatastore {
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<UnsplashData>
}
