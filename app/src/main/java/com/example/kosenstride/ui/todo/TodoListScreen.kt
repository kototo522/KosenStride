package com.example.kosenstride.ui.todo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.kosenstride.ui.todo.component.ListCard
import com.example.kosenstride.ui.todo.component.ListSortButton

data class CardItem(
    val title: String,
    val text: String,
    val dateTime: String,
    var notifications: Boolean,
    var share: Boolean,
)

val CardItemList =
    listOf(
        CardItem(
            "数値計算 WebClass",
            "問題１",
            "2023/8/20/17:00",
            notifications = false,
            share = true,
        ),
        CardItem(
            "数値計算 WebClass",
            "問題2",
            "2023/8/20/18:00",
            notifications = true,
            share = false,
        ),
        CardItem(
            "数値計算 WebClass",
            "問題3",
            "2023/8/20/19:00",
            notifications = false,
            share = true,
        ),
    )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(navController: NavHostController, viewModel: TodoListViewModel = hiltViewModel()) {
    val todoUiState by viewModel.uiState.collectAsState()
    val expanded = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("createTodo") },
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(top = 16.dp)) {
                Box(modifier = Modifier.align(Alignment.End)) {
                    ListSortButton(expanded = expanded)
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    itemsIndexed(todoUiState.todo) { index, todo ->
                        ListCard(index, todo)
                    }
                }
            }
        }
    }
}
