package com.example.cactus.api.responses

data class UnsplashSearchResponse(
    val results: List<UnsplashPhoto>? = null,
    var totalPages: Int? = null
)
