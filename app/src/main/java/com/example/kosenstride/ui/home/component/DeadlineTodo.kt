package com.example.kosenstride.ui.home.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kosenstride.ui.home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeadlineTodo(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val sortedTodoList = remember(homeUiState.todo) {
        homeUiState.todo.sortedBy { it.dateTime }
    }
    val smallTodoList = sortedTodoList.take(2)

    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "締切間近",
            fontSize = 18.sp,
            fontWeight = FontWeight(700),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start,
        ) {
            itemsIndexed(smallTodoList) { index, todo ->
                DeadlineTodoCard(smallTodoList[index], viewModel)
            }
        }
    }
}
