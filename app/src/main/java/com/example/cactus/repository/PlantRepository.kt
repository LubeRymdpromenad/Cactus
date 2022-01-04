package com.example.cactus.repository

import android.content.Context
import com.example.cactus.R
import com.example.cactus.data.PlantData
import com.example.cactus.common.jsonToClass
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PlantRepository(
    @ApplicationContext val context: Context,
) : PlantDataStore {
    override fun getPlants(): Flow<List<PlantData>> {
        return flow {
            val list: List<PlantData> = context.jsonToClass(R.raw.plants)
            emit(list)
        }
    }
}
