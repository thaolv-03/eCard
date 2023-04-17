package com.example.ecard.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(
        route = "home",
        icon = Icons.Outlined.Home,
    ),
    BottomNavItem(
        route = "share",
        icon = Icons.Outlined.QrCode2
    ),
    BottomNavItem(
        route = "scan",
        icon = Icons.Outlined.QrCodeScanner,
    ),
    BottomNavItem(
        route = "edit",
        icon = Icons.Outlined.Edit,
    ),
    BottomNavItem(
        route = "setting",
        icon = Icons.Outlined.Settings,
    ),
)

@Composable
fun BottomBarEdit() {
    NavigationBar(
        modifier = Modifier.height(70.dp),
        containerColor = MaterialTheme.colors.background
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            val navigationBarItemColors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colors.onSurface,
                indicatorColor = MaterialTheme.colors.surface,
                unselectedIconColor = MaterialTheme.colors.onSurface
            )

            bottomNavItems.forEach {
                NavigationBarItem(
                    selected = it.route == "home",
                    onClick = { },
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