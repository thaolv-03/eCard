package com.example.ecard.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecard.ui.contact.ContactDestination
import com.example.ecard.ui.contact.ContactScreen
import com.example.ecard.ui.edit.EditDestination
import com.example.ecard.ui.edit.EditScreen
import com.example.ecard.ui.home.HomeDestination
import com.example.ecard.ui.home.HomeScreen
import com.example.ecard.ui.scan.ScanDestination
import com.example.ecard.ui.scan.ScanScreen
import com.example.ecard.ui.share.ShareDestination
import com.example.ecard.ui.share.ShareScreen
import com.example.ecard.ui.sign_in.SignInDestination
import com.example.ecard.ui.sign_in.SignInScreen

@Composable
fun ECardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
//        startDestination = HomeDestination.route,
//        startDestination = SignInDestination.route,
        startDestination = ShareDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }

        composable(ShareDestination.route) {
            ShareScreen(
                navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }

        composable(ContactDestination.route) {
            ContactScreen(
                navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }

        composable(route = SignInDestination.route) {
            SignInScreen()
        }

        composable(route = ScanDestination.route) {
            ScanScreen(navigateTo = { navController.navigate(it) },
                currentDestination = navController.currentDestination
            )
        }

        composable(
            route = HomeDestination.routeWithArgs,
            arguments = listOf(navArgument(HomeDestination.userIdArg) {
                type = NavType.IntType
                defaultValue = 0
            })
        ) {
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