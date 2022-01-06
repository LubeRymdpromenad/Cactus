package com.example.cactus.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cactus.data.PlantData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class PlantDetailViewState(
    val plantData: PlantData = PlantData()
)

@HiltViewModel
class PlantDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
)  : ViewModel() {
    val viewState = mutableStateOf(PlantDetailViewState())

    private val data: PlantData? = savedStateHandle.get<PlantData>(PLANT_DATA_SAVED_STATE_KEY)

    init {
        data?.let {
            viewState.value = viewState.value.copy(plantData = it)
        }
    }

    companion object {
        private const val PLANT_DATA_SAVED_STATE_KEY = "plantData"
    }
}
