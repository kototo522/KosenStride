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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.kosenstride.ui.todo.component.ListCard
import com.example.kosenstride.ui.todo.component.ListSortButton
import kotlinx.coroutines.launch

@Composable
fun ToDoListScreen(navController: NavHostController, viewModel: TodoListViewModel = hiltViewModel()) {
    val todoUiState by viewModel.uiState.collectAsState()
    val expanded = remember { mutableStateOf(false) }
    val sortType = remember { mutableStateOf("追加順") }


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
                    ListSortButton(expanded = expanded, sortType = sortType)
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    itemsIndexed(todoUiState.todo) { index , todo ->
                        val sortedTodoList = if(sortType.value == "期限の早い順") {
                            remember(todoUiState.todo) {
                                todoUiState.todo.sortedBy { it.dateTime }
                            }
                        } else if(sortType.value == "期限の遅い順") {
                            remember(todoUiState.todo) {
                                todoUiState.todo.sortedByDescending { it.dateTime }
                            }
                        } else {
                            todoUiState.todo
                        }
                        ListCard(sortedTodoList[index], viewModel)
                    }
                }
            }
        }
    }
}
