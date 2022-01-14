package com.example.cactus.repository

import com.example.cactus.data.PlantData
import kotlinx.coroutines.flow.Flow

interface PlantDataStore {
    fun getPlants(): Flow<List<PlantData>>
    fun getPlant(id: String): Flow<PlantData?>
}
