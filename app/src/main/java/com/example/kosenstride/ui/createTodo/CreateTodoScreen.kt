package com.example.kosenstride.ui.createTodo

import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kosenstride.navigation.BottomBarScreen

@Composable
fun CreateTodoScreen(navController: NavController) {
    IconButton(onClick = { navController.navigate(route = BottomBarScreen.ToDoList.route) }) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "閉じる",
            modifier = Modifier.width(20.dp),
        )
    }
}