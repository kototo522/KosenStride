package com.example.kosenstride.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(route = "home", title = "Home", icon = Icons.Default.DateRange)
    object ToDoList : BottomBarScreen(route = "todoList", title = "ToDoList", icon = Icons.Default.List)
    object Setting : BottomBarScreen(route = "setting", title = "Setting", icon = Icons.Default.Settings)
}