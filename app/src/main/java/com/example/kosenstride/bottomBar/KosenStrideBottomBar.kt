package com.example.kosenstride.bottomBar

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.kosenstride.navigation.BottomBarItems

@Composable
fun KosenStrideBottomBar(screenItems:  List<BottomBarItems>, currentRoute: String?, navController: NavHostController) {
    NavigationBar(
        modifier =
        Modifier
            .background(MaterialTheme.colorScheme.background),
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        screenItems.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}