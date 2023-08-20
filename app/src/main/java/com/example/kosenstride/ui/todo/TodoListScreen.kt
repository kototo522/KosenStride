package com.example.kosenstride.ui.todo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kosenstride.ui.todo.component.ListCard

@Composable
fun ToDoListScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "ToDoList Screen", style = MaterialTheme.typography.bodyMedium)
        ListCard()
    }
}