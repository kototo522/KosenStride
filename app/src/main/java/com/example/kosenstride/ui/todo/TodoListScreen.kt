package com.example.kosenstride.ui.todo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.todo.component.ListCard

data class CardItem(
    val title: String?,
    val text: String?,
    val dateTime: String,
    var notifications: Boolean,
    var share: Boolean,
)

val CardItemList = listOf(
    CardItem("数値計算 WebClass", "問題１", "2023/8/20/17:00",
        notifications = false,
        share = true
    ),
    CardItem("数値計算 WebClass", "問題2", "2023/8/20/18:00",
        notifications = true,
        share = false
    ),
    CardItem("数値計算 WebClass", "問題3", "2023/8/20/19:00",
        notifications = false,
        share = true
    ),
)
@Composable
fun ToDoListScreen(){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        ) {
        itemsIndexed(CardItemList) { index, CardItem ->
            ListCard(index, CardItemList[index])
        }
    }
}

@Preview
@Composable
fun PreviewTodoListScreen() {
    ToDoListScreen()
}