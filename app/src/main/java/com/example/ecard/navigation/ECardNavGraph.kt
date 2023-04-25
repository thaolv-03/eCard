package com.example.ecard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecard.ui.edit.EditDestination
import com.example.ecard.ui.edit.EditScreen
import com.example.ecard.ui.home.HomeDestination
import com.example.ecard.ui.home.HomeScreen

@Composable
fun ECardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }

        composable(route = EditDestination.route) {
            EditScreen(
                navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }
    }
}