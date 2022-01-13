package com.example.cactus.data.mapper

import com.example.cactus.api.responses.UnsplashSearchResponse
import com.example.cactus.common.Mapper
import com.example.cactus.data.UnsplashData

class UnsplashResponseMapper : Mapper<UnsplashSearchResponse, List<UnsplashData>> {
    override fun map(model: UnsplashSearchResponse): List<UnsplashData> {
        return mutableListOf<UnsplashData>().also { list ->
            model.results?.map {
                list.add(UnsplashData(it.id.orEmpty(), it.user?.name, it.urls?.small, it.user?.attributionUrl))
            }
        }
    }
}
