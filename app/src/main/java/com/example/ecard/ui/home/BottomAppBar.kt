package com.example.ecard.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
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
        icon = Icons.Outlined.EditNote,
    ),
    BottomNavItem(
        route = "setting",
        icon = Icons.Outlined.Settings,
    ),
)

@Composable
fun BottomBarEdit() {
    NavigationBar(
        modifier = Modifier
            .height(70.dp).shadow(elevation = 10.dp),
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