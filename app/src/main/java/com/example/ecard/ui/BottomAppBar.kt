package com.example.ecard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.ecard.ui.edit.EditDestination
import com.example.ecard.ui.home.HomeDestination
import com.example.ecard.ui.scan.ScanDestination
import com.example.ecard.ui.share.ShareDestination

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = HomeDestination.route,
        icon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        route = "contact",
        icon = Icons.Outlined.Group
    ),
    BottomNavItem(
        route = ScanDestination.route,
        icon = Icons.Outlined.QrCodeScanner,
    ),
    BottomNavItem(
        route = ShareDestination.route,
        icon = Icons.Outlined.QrCode2,
    ),
    BottomNavItem(
        route = "setting",
        icon = Icons.Outlined.Settings,
    ),
)

@Composable
fun BottomBarEdit(
    currentDestination: NavDestination? = null,
    navigateTo: (String) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .height(70.dp)
            .shadow(elevation = 10.dp),
        containerColor = MaterialTheme.colors.background,

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val navigationBarItemColors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colors.onSurface,
                indicatorColor = MaterialTheme.colors.surface,
                unselectedIconColor = MaterialTheme.colors.onSurface
            )

            bottomNavItems.forEach {
                NavigationBarItem(
                    selected = it.route == (currentDestination?.route
                        ?: false),
                    onClick = { navigateTo(it.route) },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = "${it.route} icon",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    colors = navigationBarItemColors
                )
            }
        }
    }
}