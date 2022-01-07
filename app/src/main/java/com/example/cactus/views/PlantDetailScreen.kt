package com.example.cactus.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.cactus.R
import com.example.cactus.data.PlantData
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.PlantDetailViewState


@Composable
fun PlantDetailScreen(
    plantDetailViewState: PlantDetailViewState,
    plantData: PlantData,
    onSearchClick: (String) -> Unit
) {

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

        IconButton(
            onClick = { onSearchClick.invoke(plantData.name) },
        ){
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search button",
            )
        }

        Text(
            text = plantData.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}


class SamplePlantDataProvider: PreviewParameterProvider<PlantData> {
    override val values = sequenceOf(
        PlantData(
            name = "Apple",
            description = "An apple is a sweet, edible fruit produced by an apple tree (Malus pumila). Apple trees are cultivated worldwide, and are the most widely grown species in the genus Malus. The tree originated in Central Asia, where its wild ancestor, Malus sieversii, is still found today. Apples have been grown for thousands of years in Asia and Europe, and were brought to North America by European colonists. Apples have religious and mythological significance in many cultures, including Norse, Greek and European Christian traditions.<br><br>Apple trees are large if grown from seed. Generally apple cultivars are propagated by grafting onto rootstocks, which control the size of the resulting tree. There are more than 7,500 known cultivars of apples, resulting in a range of desired characteristics. Different cultivars are bred for various tastes and uses, including cooking, eating raw and cider production. Trees and fruit are prone to a number of fungal, bacterial and pest problems, which can be controlled by a number of organic and non-organic means. In 2010, the fruit's genome was sequenced as part of research on disease control and selective breeding in apple production.<br><br>Worldwide production of apples in 2014 was 84.6 million tonnes, with China accounting for 48% of the total.<br><br>(From <a href=\"https://en.wikipedia.org/wiki/Apple\">Wikipedia</a>)",
        )
    )
}

@Preview
@Composable
fun DefaultPreview(
    @PreviewParameter(SamplePlantDataProvider::class) plantData: PlantData
) {
    CactusTheme {
        PlantDetailScreen(PlantDetailViewState(), plantData, {})
    }
}
