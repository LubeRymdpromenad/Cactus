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
import androidx.navigation.navDeepLink
import com.example.cactus.viewmodels.PlantDetailViewModel
import com.example.cactus.viewmodels.PlantListViewModel
import com.example.cactus.viewmodels.UnsplashViewModel

/**
 * To test deeplink type following in terminal
 *
 * For detail page:
 * <code>adb shell am start -d "cactus://plantdetail/beta-vulgaris" -a android.intent.action.VIEW</code>
 *
 * For unsplash list:
 * <code>adb shell am start -d "cactus://unsplashlist/Tomato" -a android.intent.action.VIEW</code>
 */
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
                    navController.navigate("${CactusScreen.PlantDetail.name}/${it.plantId}")
                }
            )
        }
        val plantDetailName = CactusScreen.PlantDetail.name
        composable(
            route = "$plantDetailName/{plantId}",
            arguments = listOf(
                navArgument("plantId") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern = "cactus://$plantDetailName/{plantId}"
            })
        ) {
            val plantDetailViewModel = hiltViewModel<PlantDetailViewModel>()
            val plantDetailViewState by plantDetailViewModel.viewState
            PlantDetailScreen(
                plantData = plantDetailViewState.plantData,
                onSearchClick = { query ->
                    navController.navigate("${CactusScreen.UnsplashList.name}/${query}")
                }
            )
        }
        val unsplashListName = CactusScreen.UnsplashList.name
        composable(
            route = "$unsplashListName/{query}",
            arguments = listOf(
                navArgument("query") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(navDeepLink {
                uriPattern = "cactus://$unsplashListName/{query}"
            })
        ) {
            val unsplashViewModel = hiltViewModel<UnsplashViewModel>()
            UnsplashScreen(unsplashViewModel.getUnsplashData())
        }
    }
}
