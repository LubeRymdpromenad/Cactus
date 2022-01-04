package com.example.cactus.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cactus.data.PlantData
import com.example.cactus.viewmodels.repository.PlantDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlantListViewState(
    val plantList: List<PlantData> = emptyList()
)

@HiltViewModel
class PlantListViewModel @Inject constructor(
    plantDataStore: PlantDataStore
) : ViewModel() {
    val viewState = mutableStateOf(PlantListViewState())

    init {
        viewModelScope.launch {
            plantDataStore.getPlants().collect {
                viewState.value = viewState.value.copy(plantList = it)
            }
        }
    }
}
