package com.example.cactus.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.UnsplashViewState


@Composable
fun UnsplashScreen(unsplashViewState: UnsplashViewState) {
    Text(
        text = unsplashViewState.query,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    )
}


@Preview
@Composable
fun UnsplashScreenPreview() {
    CactusTheme {
        UnsplashScreen(UnsplashViewState())
    }
}
