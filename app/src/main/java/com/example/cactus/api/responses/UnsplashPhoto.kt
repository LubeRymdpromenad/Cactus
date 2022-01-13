package com.example.cactus.api.responses

data class UnsplashPhoto(
    val id: String? = null,
    val urls: UnsplashPhotoUrls? = null,
    var user: UnsplashUser? = null
)
