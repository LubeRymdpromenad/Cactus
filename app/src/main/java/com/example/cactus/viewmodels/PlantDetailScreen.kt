package com.example.cactus.viewmodels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.cactus.R
import com.example.cactus.data.PlantData
import com.example.cactus.ui.theme.CactusTheme


@Composable
fun PlantDetailScreen(plantDetailViewState: PlantDetailViewState, plantData: PlantData) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Image(
            modifier = Modifier.height(220.dp),

            painter = rememberImagePainter(
                plantData.imageUrl,
                builder = {
                    size(OriginalSize)
                    placeholder(R.drawable.placeholder)
                    crossfade(true)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(
            text = plantData.name,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        )

        Text(
            text = plantData.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CactusTheme {
        PlantDetailScreen(PlantDetailViewState(), PlantData())
    }
}
