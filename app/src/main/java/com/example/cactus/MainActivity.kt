package com.example.cactus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.*
import com.example.cactus.views.CactusScreen
import com.example.cactus.views.PlantListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val plantDetailViewModel: PlantDetailViewModel by viewModels()
    private val plantListViewModel: PlantListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val plantListViewState by plantListViewModel.viewState
        val plantDetailViewState by plantDetailViewModel.viewState
        setContent {
            CactusMainScreen(
                plantListViewState = plantListViewState,
                plantDetailViewState = plantDetailViewState
            )
        }
    }
}

@Composable
fun CactusMainScreen(
    plantListViewState: PlantListViewState,
    plantDetailViewState: PlantDetailViewState
) {
    CactusTheme {
        val allScreens = CactusScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = CactusScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )

        Scaffold(
//            topBar = {
//                RallyTabRow(
//                    allScreens = allScreens,
//                    onTabSelected = { screen ->
//                        navController.navigate(screen.name)
//                        currentScreen = screen
//                    },
//                    currentScreen = currentScreen
//                )
//            }
        ) { innerPadding ->
            CactusNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                plantListViewState,
                plantDetailViewState
            )
        }
    }
}

@Composable
fun CactusNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    plantListViewState: PlantListViewState,
    plantDetailViewState: PlantDetailViewState
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
            ) { navController.navigate(CactusScreen.PlantDetail.name) }
        }
        composable(
            route = CactusScreen.PlantDetail.name,
            arguments = listOf(
                navArgument("plantData") {
                    type = NavType.StringType
                }
            ),


        ) {
            PlantDetailScreen(plantDetailViewState = plantDetailViewState)
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
