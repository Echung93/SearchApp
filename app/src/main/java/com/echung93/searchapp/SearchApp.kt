package com.echung93.searchapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.echung93.searchapp.designsystem.component.AppNavigationBar
import com.echung93.searchapp.designsystem.component.AppNavigationBarItem
import com.echung93.searchapp.navigation.NavHost
import com.echung93.searchapp.navigation.TopLevelDestination
import com.echung93.searchapp.presentation.splash.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun SearchApp() {
    val navHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = showSplashScreen) {
        delay(3000)
        showSplashScreen = false
    }

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            if(!showSplashScreen) {
                AppBottomBar(
                    destination = TopLevelDestination.values().toList(),
                    onNavigateToDestination = { destination ->
                        navHostController.navigate(destination.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    currentDestination = backStackEntry?.destination
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
        ) {
            if (showSplashScreen) SplashScreen()
            else NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navHostController,
                snackbarHostState = snackbarHostState
            )
        }
    }
}

@Composable
private fun AppBottomBar(
    destination: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    AppNavigationBar(
        modifier = modifier
    ) {
        destination.forEach { destination ->
            val isSelected =
                currentDestination?.route == destination.route


            AppNavigationBarItem(
                selected = isSelected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                selectedIcon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = null,
                    )
                },
                label = destination.iconTextId,
                alwaysShowLabel = false
            )
        }
    }
}
