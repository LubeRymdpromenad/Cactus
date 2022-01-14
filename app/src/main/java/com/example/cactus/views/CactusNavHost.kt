package com.example.cactus.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cactus.data.PlantData
import com.example.cactus.data.PlantDataParamType
import com.example.cactus.data.toJson
import com.example.cactus.viewmodels.PlantDetailViewModel
import com.example.cactus.viewmodels.PlantListViewModel
import com.example.cactus.viewmodels.UnsplashViewModel

@Composable
fun CactusNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CactusScreen.PlantList.name,
        modifier = modifier
    ) {
        composable(
            route = CactusScreen.PlantList.name
        ) {
            val plantListViewModel = hiltViewModel<PlantListViewModel>()
            val plantListViewState by plantListViewModel.viewState

            PlantListScreen(
                viewState = plantListViewState,
                onItemClick = {
                    navController.navigate("${CactusScreen.PlantDetail.name}/${it.toJson()}")
                }
            )
        }
        val plantDetailName = CactusScreen.PlantDetail.name
        composable(
            route = "$plantDetailName/{plantData}",
            arguments = listOf(
                navArgument("plantData") {
                    type = PlantDataParamType
                }
            )
        ) { entry ->
            val plantData = entry.arguments?.getParcelable<PlantData>("plantData")
            plantData?.let { data ->
                val plantDetailViewModel = hiltViewModel<PlantDetailViewModel>()
                val plantDetailViewState by plantDetailViewModel.viewState
                PlantDetailScreen(
                    plantDetailViewState = plantDetailViewState,
                    plantData = data,
                    onSearchClick = { query ->
                        navController.navigate("${CactusScreen.UnsplashList.name}/${query}")
                    }
                )
            }
        }
        val unsplashName = CactusScreen.UnsplashList.name
        composable(
            route = "$unsplashName/{query}",
            arguments = listOf(
                navArgument("query") {
                    type = NavType.StringType
                }
            )
        ) {
            val unsplashViewModel = hiltViewModel<UnsplashViewModel>()
            UnsplashScreen(unsplashViewModel.getUnsplashData())
        }
    }
}
