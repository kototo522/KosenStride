package com.example.kosenstride.ui.createTodo

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kosenstride.navigation.BottomBarScreen
import com.example.kosenstride.ui.createTodo.component.AddItem
import com.example.kosenstride.ui.createTodo.component.ChangeDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(navController: NavController) {
    val datePickerState =
        rememberDatePickerState(
            initialSelectedDateMillis = Instant.now().toEpochMilli(),
        )
    val timePickerState = rememberTimePickerState()
    var addTitleText by remember { mutableStateOf("") }
    var addText by remember { mutableStateOf("") }
    val dateText =
        remember {
            mutableStateOf(
                ChangeDateFormat(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())),
            )
        }
    val timeText = remember { mutableStateOf("23:59") }
    val checkedState = remember { mutableStateOf(false) }
    var datePickerExpended by remember { mutableStateOf(false) }
    var timePickerExpended by remember { mutableStateOf(false) }
    val context: Context = LocalContext.current

    val createItems =
        listOf(
            AddItem("タイトル", addTitleText) { addTitleText = it },
            AddItem("内容", addText) { addText = it },
        )

    Column(modifier = Modifier.padding(vertical = 24.dp)) {
        createItems.forEach { item ->
            Text(text = item.name, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
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

        Text(text = "期限", fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp))
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text(
                text = dateText.value,
                fontSize = 16.sp,
                modifier =
                Modifier
                    .padding(horizontal = 20.dp)
                    .clickable { datePickerExpended = true },
            )
            Text(
                text = timeText.value,
                fontSize = 16.sp,
                modifier =
                Modifier
                    .padding(horizontal = 20.dp)
                    .clickable { timePickerExpended = true },
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

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

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    navController.navigate(route = BottomBarScreen.ToDoList.route)
                },
            ) {
                Text(text = "キャンセル")
            }
            Button(
                onClick = {
                    if (addTitleText == "" || addText == "") Toast.makeText(context, "入力されていない箇所があります", Toast.LENGTH_LONG).show()
                    else navController.navigate(route = BottomBarScreen.ToDoList.route)
                },
            ) {
                Text(text = "追加")
            }
        }

    }
    if (datePickerExpended) {
        DatePickerDialog(
            onDismissRequest = { datePickerExpended = false },
            dismissButton = {
                Text(text = "キャンセル", modifier = Modifier
                    .padding(10.dp)
                    .clickable { datePickerExpended = false })
            },
            confirmButton = {
                Text(
                    text = "OK",
                    modifier =
                    Modifier
                        .padding(10.dp)
                        .clickable {
                            val selectedDateMillis = datePickerState.selectedDateMillis
                            dateText.value = ChangeDateFormat(Date(selectedDateMillis!!))
                            datePickerExpended = false
                        },
                )
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
    if (timePickerExpended) {
        DatePickerDialog(
            onDismissRequest = { timePickerExpended = false },
            dismissButton = {
                Text(text = "キャンセル", modifier = Modifier
                    .padding(10.dp)
                    .clickable { timePickerExpended = false })
            },
            confirmButton = {
                Text(
                    text = "OK",
                    modifier =
                    Modifier
                        .padding(10.dp)
                        .clickable {
                            timeText.value = "${timePickerState.hour}:${timePickerState.minute}"
                            timePickerExpended = false
                        },
                )
            },
        ) {
            TimePicker(state = timePickerState, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}
