package com.example.cactus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.PlantListViewModel
import com.example.cactus.viewmodels.PlantListViewState
import com.example.cactus.views.PlantListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val plantListViewModel: PlantListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val plantListViewState by plantListViewModel.viewState

        setContent {
            CactusTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PlantListScreen(plantListViewState)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CactusTheme {
        PlantListScreen(PlantListViewState())
    }
}