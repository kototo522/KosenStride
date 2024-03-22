package com.example.kosenstride.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kosenstride.navigation.bottomBar.KosenStrideBottomBar
import com.example.kosenstride.navigation.topAppBar.KosenStrideTopAppBar
import com.example.kosenstride.ui.settingItems.editTimeSchedule.EditTimeScheduleScreen
import com.example.kosenstride.ui.chat.ChatScreen
import com.example.kosenstride.ui.createTodo.CreateTodoScreen
import com.example.kosenstride.ui.home.HomeScreen
import com.example.kosenstride.ui.myAccount.MyAccountScreen
import com.example.kosenstride.ui.setting.SettingScreen
import com.example.kosenstride.ui.todo.ToDoListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val screenItems =
        listOf(
            BottomBarItems.Home,
            BottomBarItems.ToDoList,
            BottomBarItems.Chat,
        )
    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navStackBackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == BottomBarItems.Home.route || currentRoute == BottomBarItems.ToDoList.route || currentRoute == BottomBarItems.Chat.route) {
                KosenStrideTopAppBar(
                    title = {
                        Text(
                            text = "Kosen Stride",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(700),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.7.sp,
                            ),
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigate("MyAccount") }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "アカウントアイコン",
                                modifier = Modifier.width(24.dp),
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate("setting") }) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "設定")
                        }
                    },
                )
            } else {
                KosenStrideTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "戻る",
                                modifier = Modifier.width(24.dp),
                            )
                        }
                    },
                    actions = {},
                )
            }
        },
        bottomBar = {
            if (currentRoute == BottomBarItems.Home.route || currentRoute == BottomBarItems.ToDoList.route || currentRoute == BottomBarItems.Chat.route) {
                KosenStrideBottomBar(screenItems = screenItems, currentRoute = currentRoute, navController = navController)
            }
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = BottomBarItems.Home.route,
            ) {
                composable(route = BottomBarItems.Home.route) {
                    HomeScreen()
                }
                composable(route = BottomBarItems.ToDoList.route) {
                    ToDoListScreen(navController)
                }
                composable(route = BottomBarItems.Chat.route) {
                    ChatScreen()
                }
                composable(route = "createTodo") {
                    CreateTodoScreen(navController)
                }
                composable(route = "myAccount") {
                    MyAccountScreen(navController)
                }
                composable(route = "setting") {
                    SettingScreen(navController)
                }
                composable(route = "editTimeSchedule") {
                    EditTimeScheduleScreen()
                }
            }
        }
    }
}
