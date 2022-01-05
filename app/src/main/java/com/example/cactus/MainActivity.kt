package com.example.cactus

import android.net.Uri
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
import com.example.cactus.common.jsonToClass
import com.example.cactus.data.PlantData
import com.example.cactus.data.PlantDataParamType
import com.example.cactus.ui.theme.CactusTheme
import com.example.cactus.viewmodels.*
import com.example.cactus.views.CactusScreen
import com.example.cactus.views.PlantListScreen
import com.google.gson.Gson
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
//            PlantListScreen(viewState = plantListViewState, {})
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
//                onItemClick = {}
                onItemClick = {
                    val json = Uri.encode(Gson().toJson(it))
                    navController.navigate("${CactusScreen.PlantDetail.name}/$json")


//                    navController.currentBackStackEntry?.arguments =
//                        Bundle().apply {
//                            putParcelable("bt_device", device)
//                        }
//                    navController.navigate("deviceDetails")
                }
            )
//            { navController.navigate(CactusScreen.PlantDetail.name) }
        }
        val plantDetailName = CactusScreen.PlantDetail.name
        composable(
            route = "$plantDetailName/{data}",
            arguments = listOf(
                navArgument("data") {
//                    type = PlantDataParamType()
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val data = entry.arguments?.getString("data")
            data?.let {
                val plantData = it.jsonToClass<PlantData>()
                PlantDetailScreen(plantData)
            }

//            val plantData = it.arguments?.getParcelable<PlantData>("plantData")
//            plantData?.let { data ->
//                PlantDetailScreen(plantDetailViewState = plantDetailViewState, data)
//            }
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
