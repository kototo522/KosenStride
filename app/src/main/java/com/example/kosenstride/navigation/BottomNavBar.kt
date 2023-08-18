package com.example.kosenstride.navigation

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kosenstride.ui.home.HomeScreen
import com.example.kosenstride.ui.setting.SettingScreen
import com.example.kosenstride.ui.todo.ToDoListScreen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(route = "home", title = "Home", icon = Icons.Default.DateRange)
    object ToDoList : BottomBarScreen(route = "todoList", title = "ToDoList", icon = Icons.Default.List)
    object Setting : BottomBarScreen(route = "setting", title = "Setting", icon = Icons.Default.Settings)
}

    @Composable
    fun BottomNavigationBar() {
        val navController = rememberNavController()
        val screenItems = listOf(
            BottomBarScreen.Home,
            BottomBarScreen.ToDoList,
            BottomBarScreen.Setting
        )

        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route

        Surface {
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Home.route
            ) {
                composable(route = BottomBarScreen.Home.route) {
                    HomeScreen()
                }
                composable(route = BottomBarScreen.ToDoList.route) {
                    ToDoListScreen()
                }
                composable(route = BottomBarScreen.Setting.route) {
                    SettingScreen()
                }
            }
            NavigationBar(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                contentColor = MaterialTheme.colorScheme.primary
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
                        }
                    )
                }
            }
        }
    }


