package com.example.cactus.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.example.cactus.data.UnsplashData
import com.example.cactus.data.PlantData
import kotlinx.coroutines.flow.Flow
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.size.OriginalSize


@Composable
fun UnsplashScreen(unsplashList: Flow<PagingData<UnsplashData>>) {

    val lazyGameItems = unsplashList.collectAsLazyPagingItems()
    LazyColumn(
        content = {
            items(lazyGameItems.itemCount) { index ->
                lazyGameItems[index]?.let {
                    UnsplashListItem(it, {})
                }
            }
        }
    )
}


@Composable
fun UnsplashListItem(
    unsplashData: UnsplashData,
    onItemClick: (PlantData) -> Unit) {
    Row(
        modifier = Modifier.clickable(
            onClick = {
//                onItemClick.invoke(plantData)
            }
        )
    ) {
        Image(
            modifier = Modifier.size(120.dp, 90.dp),
            painter = rememberImagePainter(
                unsplashData.imageUrl,
                builder = {
                    size(OriginalSize)
                    crossfade(true)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Text(text = unsplashData.name.orEmpty(), Modifier.padding(16.dp))
    }
}

//@Preview
//@Composable
//fun UnsplashScreenPreview() {
//    CactusTheme {
//        UnsplashScreen(UnsplashViewState())
//    }
//}
