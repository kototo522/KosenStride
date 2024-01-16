package com.example.kosenstride.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kosenstride.topAppBar.KosenStrideTopAppBar
import com.example.kosenstride.ui.chat.ChatScreen
import com.example.kosenstride.ui.createTodo.CreateTodoScreen
import com.example.kosenstride.ui.home.HomeScreen
import com.example.kosenstride.ui.myAccount.MyAccountScreen
import com.example.kosenstride.ui.setting.SettingScreen
import com.example.kosenstride.ui.todo.ToDoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenItems =
        listOf(
            BottomBarScreen.Home,
            BottomBarScreen.ToDoList,
            BottomBarScreen.Chat,
        )
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == BottomBarScreen.Home.route || currentRoute == BottomBarScreen.ToDoList.route || currentRoute == BottomBarScreen.Chat.route) {
                KosenStrideTopAppBar(
                    navController,
                )
            } else {
                TopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "戻る",
                                modifier = Modifier.width(24.dp),
                            )
                        }
                    },
                    colors =
                        TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        ),
                )
            }
        },
        bottomBar = {
            if (currentRoute == BottomBarScreen.Home.route || currentRoute == BottomBarScreen.ToDoList.route || currentRoute == BottomBarScreen.Chat.route) {
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
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
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
                composable(route = BottomBarScreen.Chat.route) {
                    ChatScreen()
                }
                composable(route = "createTodo") {
                    CreateTodoScreen(navController)
                }
                composable(route = "myAccount") {
                    MyAccountScreen(navController)
                }
                composable(route = "setting") {
                    SettingScreen()
                }
            }
        }
    }
}
