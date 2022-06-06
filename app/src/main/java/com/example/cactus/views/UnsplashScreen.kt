package com.example.cactus.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.cactus.data.UnsplashData
import com.example.cactus.data.PlantData
import kotlinx.coroutines.flow.Flow
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.example.cactus.R


@Composable
fun UnsplashScreen(
    unsplashList: Flow<PagingData<UnsplashData>>,
    onItemClick: (String) -> Unit,
    onError: (Int) -> Unit
) {

    val lazyItems = unsplashList.collectAsLazyPagingItems()
    LazyColumn(
        content = {
            items(lazyItems.itemCount) { index ->
                lazyItems[index]?.let {
                    UnsplashListItem(it) { unsplashData ->
//                        onError(R.string.generic_error)
                        unsplashData.attributionUrl?.let { url ->
                            onItemClick(url)
                        }
                    }
                }
            }
            lazyItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        onError(R.string.generic_error)
                    }
                    loadState.append is LoadState.Error -> {
                        onError(R.string.generic_error)
                    }
                }
            }
        }
    )
}

@Composable
fun UnsplashListItem(
    unsplashData: UnsplashData,
    onItemClick: (UnsplashData) -> Unit
) {
    Row(
        modifier = Modifier.clickable(
            onClick = {
                onItemClick(unsplashData)
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

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier
            .testTag("ProgressBarItem")
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}

//@Preview
//@Composable
//fun UnsplashScreenPreview() {
//    CactusTheme {
//        UnsplashScreen(UnsplashViewState())
//    }
//}
