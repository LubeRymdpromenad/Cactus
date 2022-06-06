package com.example.cactus.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cactus.R
import com.example.cactus.ui.theme.CactusTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CactusMainScreen() {
    CactusTheme {
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        var currentScreen = CactusScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )

        val scaffoldState = rememberScaffoldState()
        val coroutineScope = rememberCoroutineScope()
        val resources = LocalContext.current.resources

        Scaffold(
            scaffoldState = scaffoldState,
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
                onError = { message ->
                    val msg = resources.getString(message)
                    showSnackBar(coroutineScope, scaffoldState.snackbarHostState, msg)
                }
            )
        }
    }
}

private fun showSnackBar(coroutineScope: CoroutineScope, snackbarHostState: SnackbarHostState, message: String) {
    coroutineScope.launch {
        snackbarHostState.showSnackbar(message = message)
    }
}

