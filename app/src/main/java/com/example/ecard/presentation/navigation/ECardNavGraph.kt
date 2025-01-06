package com.example.ecard.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ecard.presentation.contact.ContactDestination
import com.example.ecard.presentation.contact.ContactScreen
import com.example.ecard.presentation.edit.EditDestination
import com.example.ecard.presentation.edit.EditScreen
import com.example.ecard.presentation.home.HomeDestination
import com.example.ecard.presentation.home.HomeScreen
import com.example.ecard.presentation.scan.ScanDestination
import com.example.ecard.presentation.scan.ScanScreen
import com.example.ecard.presentation.setting.SettingDestination
import com.example.ecard.presentation.setting.SettingScreen
import com.example.ecard.presentation.share.ShareDestination
import com.example.ecard.presentation.share.ShareScreen
import com.example.ecard.presentation.sign_in.SignInDestination
import com.example.ecard.presentation.sign_in.SignInScreen

@Composable
fun ECardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
//        startDestination = HomeDestination.route,
        startDestination = SignInDestination.route,
//        startDestination = ShareDestination.route,
//        startDestination = ScanDestination.route,
        modifier = modifier
    ) {
        composable(route = SignInDestination.route) {
            SignInScreen(
                navigateTo = {
//                    if (navController.currentDestination?.route != HomeDestination.route)
//                        navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(it)
                }
            )
        }

        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateTo = {
//                    if (it == HomeDestination.route)
//                        navController.popBackStack()
//                    navController.navigate(it)
                    navigateToEdit(it, navController)

                },
                currentDestination = navController.currentDestination
            )
        }

        composable(route = EditDestination.route) {
            EditScreen(
                navigateTo = {
//                    if (navController.currentDestination?.route != HomeDestination.route)
//                        navController.popBackStack()
//                    navController.navigate(it)
                    navigateToEdit(it, navController)

                },
                currentDestination = navController.currentDestination
            )
        }

        composable(ContactDestination.route) {
            ContactScreen(
                navigateTo = {
//                    if (navController.currentDestination?.route != HomeDestination.route) {
//                        navController.popBackStack()
//                        navController.navigate(it)
//                    }
//                    navController.popBackStack()
                    navigateToEdit(it, navController)

                },
                currentDestination = navController.currentDestination
            )
        }

        composable(ShareDestination.route) {
            ShareScreen(
                navigateTo = {
//                    if (navController.currentDestination?.route != HomeDestination.route) {
//                        navController.popBackStack()
//                        navController.navigate(it)
//                    }
//                    navController.popBackStack()
                    navigateToEdit(it, navController)

                },
                currentDestination = navController.currentDestination
            )
        }

        composable(route = ScanDestination.route) {
            ScanScreen(
                navigateTo = {
//                    if (navController.currentDestination?.route != HomeDestination.route) {
//                        navController.popBackStack()
//                        navController.navigate(it)
//                    }
//                    navController.popBackStack()
                    navigateToEdit(it, navController)

                },
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
                navigateTo = {
//                    navController.popBackStack()
//                    navController.navigate(it)
                    navigateToEdit(it, navController)

                },
                currentDestination = navController.currentDestination
            )
        }

        composable(
            route = SettingDestination.route
        ) {
            SettingScreen(
                navigateTo = {
//                    navController.popBackStack()
//                    navController.navigate(it)
                    navigateToEdit(it, navController)
                },
                currentDestination = navController.currentDestination,
                onSignOut = {
                    navController.popBackStack(HomeDestination.route, true)
                    navController.navigate(SignInDestination.route)
                }
            )
        }

    }
}

fun navigateToEdit(route: String, navController: NavHostController) {
    navController.navigate(route) {
        popUpTo(HomeDestination.route)
        launchSingleTop = true
    }
}