package com.example.cactus.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.cactus.data.PlantData
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.PlantListViewState


@Composable
fun PlantListScreen(
    viewState: PlantListViewState,
    onItemClick: (PlantData) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        viewState.plantList.forEach {
            PlantListItem(it, onItemClick)
        }
    }
}

@Composable
fun PlantListItem(
    plantData: PlantData,
    onItemClick: (PlantData) -> Unit) {
    Row(
        modifier = Modifier.clickable(
            onClick = {
                onItemClick.invoke(plantData)
            }
        )
    ) {
        Image(
            modifier = Modifier.size(120.dp, 90.dp),
            painter = rememberImagePainter(
                plantData.imageUrl,
                builder = {
                    size(OriginalSize)
                    crossfade(true)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(text = plantData.name, Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CactusTheme {
        PlantListScreen(PlantListViewState()) {}
    }
}

