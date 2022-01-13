package com.example.cactus.api.responses


import com.google.gson.annotations.SerializedName

data class PlantsResponse (
    @field:SerializedName("results")
    val results: List<PlantResponse>
)
