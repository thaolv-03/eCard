package com.example.ecard.presentation.navigation

import androidx.annotation.StringRes

interface NavigationDestination {
    val route: String
    val titleRes: Int
}