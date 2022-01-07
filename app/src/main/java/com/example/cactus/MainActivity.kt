package com.example.cactus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cactus.data.PlantData
import com.example.cactus.data.PlantDataParamType
import com.example.cactus.data.toJson
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.*
import com.example.cactus.views.CactusScreen
import com.example.cactus.views.PlantDetailScreen
import com.example.cactus.views.PlantListScreen
import com.example.cactus.views.UnsplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val plantDetailViewModel: PlantDetailViewModel by viewModels()
    private val plantListViewModel: PlantListViewModel by viewModels()
    private val unsplashViewModel: UnsplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val plantListViewState by plantListViewModel.viewState
        val plantDetailViewState by plantDetailViewModel.viewState
        setContent {
            CactusMainScreen(
                plantListViewState = plantListViewState,
                plantDetailViewState = plantDetailViewState,
                unsplashViewModel = unsplashViewModel
            )
        }
    }
}

@Composable
fun CactusMainScreen(
    plantListViewState: PlantListViewState,
    plantDetailViewState: PlantDetailViewState,
    unsplashViewModel: UnsplashViewModel
) {
    CactusTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = CactusScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
             }
        ) { innerPadding ->
            CactusNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                plantListViewState,
                plantDetailViewState,
                unsplashViewModel
            )
        }
    }
}

@Composable
fun CactusNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    plantListViewState: PlantListViewState,
    plantDetailViewState: PlantDetailViewState,
    unsplashViewModel: UnsplashViewModel
) {
    NavHost(
        navController = navController,
        startDestination = CactusScreen.PlantList.name,
        modifier = modifier
    ) {
        composable(
            route = CactusScreen.PlantList.name
        ) {
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
        ) { entry ->
            val viewState by unsplashViewModel.viewState
            UnsplashScreen(viewState)

            val query = entry.arguments?.getString("query")
            unsplashViewModel.searchForMore(query)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CactusTheme {
        PlantListScreen(PlantListViewState()) {}
    }
}
