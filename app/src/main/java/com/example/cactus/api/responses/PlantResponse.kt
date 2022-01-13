package com.example.cactus.api.responses

import com.google.gson.annotations.SerializedName

data class PlantResponse(
    @field:SerializedName("plantId")
    val plantId: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("growZoneNumber")
    val growZoneNumber: Int,
    @field:SerializedName("wateringInterval")
    val wateringInterval: Int,
    @field:SerializedName("imageUrl")
    val imageUrl: String
)
