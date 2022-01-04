package com.example.cactus.viewmodels.repository

import com.example.cactus.data.PlantData
import kotlinx.coroutines.flow.Flow

interface PlantDataStore {
    fun getPlants(): Flow<List<PlantData>>
}
