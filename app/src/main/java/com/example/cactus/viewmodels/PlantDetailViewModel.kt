package com.example.cactus.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cactus.data.PlantData
import com.example.cactus.repository.PlantDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantDetailViewState(
    val plantData: PlantData = PlantData()
)

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    plantDataStore: PlantDataStore,
    savedStateHandle: SavedStateHandle,
)  : ViewModel() {
    val viewState = mutableStateOf(PlantDetailViewState())

    init {
        val plantId = savedStateHandle.get<String>("plantId")

        plantId?.let { id ->
            viewModelScope.launch {
                plantDataStore.getPlant(id).collect { plantData ->
                    plantData?.let { it ->
                        viewState.value = viewState.value.copy(plantData = it)
                    }
                }
            }
        }
    }
}
