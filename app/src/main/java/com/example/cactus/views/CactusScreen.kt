package com.example.cactus.views

enum class CactusScreen {
    PlantList, PlantDetail, UnsplashList;

    companion object {
        fun fromRoute(route: String?): CactusScreen =
            when (route?.substringBefore("/")) {
                PlantList.name -> PlantList
                PlantDetail.name -> PlantDetail
                UnsplashList.name -> UnsplashList
                null -> PlantList
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}