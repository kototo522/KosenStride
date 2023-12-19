package com.example.kosenstride.ui.todo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.align(Alignment.End).height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Icon(
                imageVector = Icons.Filled.ArrowDownward,
                contentDescription = "並び替え",
                modifier = Modifier.width(20.dp),
                tint = Color.Black
            )
            Text(text = "並び替え", fontSize = 12.sp, modifier = Modifier.height(20.dp), color = Color.Black)
        }
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
}

@Preview(backgroundColor = 244)
@Composable
fun PreviewTodoListScreen() {
    ToDoListScreen()
}