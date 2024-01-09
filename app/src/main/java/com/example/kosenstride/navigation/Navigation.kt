package com.example.kosenstride.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kosenstride.ui.createTodo.CreateTodoScreen
import com.example.kosenstride.ui.home.HomeScreen
import com.example.kosenstride.ui.setting.SettingScreen
import com.example.kosenstride.ui.todo.ToDoListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenItems =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.ToDoList,
            BottomBarScreen.Setting,
        )
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry?.destination?.route

    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Home.route,
            ) {
                composable(route = BottomBarScreen.Home.route) {
                    HomeScreen()
                }
                composable(route = BottomBarScreen.ToDoList.route) {
                    ToDoListScreen(navController)
                }
                composable(route = BottomBarScreen.Setting.route) {
                    SettingScreen()
                }
                composable(route = "createTodo") {
                    CreateTodoScreen(navController)
                }
            }
            if (currentRoute != "createTodo")
                {
                    NavigationBar(
                        modifier =
                            Modifier.align(Alignment.BottomCenter)
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
        }
    }
}
