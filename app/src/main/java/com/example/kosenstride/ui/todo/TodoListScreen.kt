package com.example.kosenstride.ui.todo
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kosenstride.ui.todo.component.ListCard

data class CardItem(
    val id: String,
    val title: String?,
    val notifications: Boolean,
    val share: Boolean,
)

val CardItemList = listOf(
    CardItem("Task 1", "数値計算 WebClass", false,false),
    CardItem("Task 2", "電気回路 プリント 課題８", true, false),
    CardItem("Task 3", "電気回路 プリント 課題９", false, false),
)
@Composable
fun ToDoListScreen(){
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(CardItemList) { index, CardItem ->
            ListCard(index, CardItemList[index], Modifier)
        }
    }
}