package com.example.kosenstride.ui.createTodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kosenstride.navigation.BottomBarScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(navController: NavController) {
    var addTitleText by remember { mutableStateOf("") }
    var addText by remember { mutableStateOf("") }
    var dateTimeText by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }

    val createItems =
        listOf(
            AddItem("タイトル", addTitleText) { addTitleText = it },
            AddItem("内容", addText) { addText = it },
            AddItem("期限", dateTimeText) { dateTimeText = it },
        )

    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(onClick = { navController.navigate(route = BottomBarScreen.ToDoList.route) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "閉じる",
                    modifier = Modifier.width(20.dp),
                )
            }
            Button(onClick = { navController.navigate(route = BottomBarScreen.ToDoList.route) }) {
                Text(text = "追加")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        createItems.forEach { item ->
            Text(text = item.name, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp))
            TextField(
                value = item.value,
                onValueChange = {
                    item.onValueChange(it)
                },
                textStyle = TextStyle(fontSize = 14.sp),
                modifier =
                    Modifier
                        .padding(vertical = 8.dp, horizontal = 20.dp)
                        .fillMaxWidth(),
                singleLine = true,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        Row(
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp),
        ) {
            Text(text = "グループ全体に公開する", fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp))
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                modifier = Modifier.height(14.dp),
            )
        }
    }
}

data class AddItem(
    val name: String,
    val value: String,
    val onValueChange: (String) -> Unit,
)
