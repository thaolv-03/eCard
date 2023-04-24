package com.example.ecard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ecard.navigation.ECardNavHost

@Composable
fun ECardApp(navController: NavHostController = rememberNavController()) {
    ECardNavHost(navController = navController)
}