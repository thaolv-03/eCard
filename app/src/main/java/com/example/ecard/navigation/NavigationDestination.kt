package com.example.ecard.navigation

import androidx.annotation.StringRes

interface NavigationDestination {
    val route: String
    val titleRes: Int
}