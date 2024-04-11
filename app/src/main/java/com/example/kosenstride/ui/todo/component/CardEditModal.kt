package com.example.kosenstride.ui.todo.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.kosenstride.data.local.entities.TodoEntity
import com.example.kosenstride.ui.todo.TodoListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardEditModal(
    isEditModalVisible: MutableState<Boolean>,
    cardItem: TodoEntity,
    viewModel: TodoListViewModel,
) {
    var editedTitleText by remember { mutableStateOf(cardItem.title) }
    var editedText by remember { mutableStateOf(cardItem.text) }
    var dateTimeLong by remember { mutableStateOf(cardItem.dateTime) }

    Column {
        Dialog(onDismissRequest = { isEditModalVisible.value = false }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "編集", fontSize = 24.sp, modifier = Modifier.padding(top = 6.dp, bottom = 18.dp))
                    Text(text = "タイトル", fontSize = 14.sp)
                    editedTitleText?.let {
                        TextField(
                            value = it,
                            onValueChange = { text ->
                                editedTitleText = text
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            modifier =
                                Modifier
                                    .padding(vertical = 8.dp, horizontal = 20.dp)
                                    .fillMaxWidth(),
                            singleLine = true,
                        )
                    }
                    Text(text = "内容", fontSize = 14.sp, modifier = Modifier.height(20.dp))
                    TextField(
                        value = editedText,
                        onValueChange = { text ->
                            editedText = text
                        },
                        textStyle = TextStyle(fontSize = 14.sp),
                        modifier =
                            Modifier
                                .padding(vertical = 8.dp, horizontal = 20.dp)
                                .fillMaxWidth(),
                    )
                    Text(text = "期限", fontSize = 14.sp, modifier = Modifier.height(20.dp))
                    dateTimeLong.toString()?.let {
                        TextField(
                            value = it,
                            onValueChange = { text ->
                                dateTimeLong = text.toLong()
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            modifier =
                                Modifier
                                    .padding(vertical = 8.dp, horizontal = 20.dp)
                                    .fillMaxWidth(),
                            singleLine = true,
                        )
                    }
                    Row(modifier = Modifier.padding(20.dp)) {
                        Button(
                            onClick = {
                                isEditModalVisible.value = false
                            },
                            modifier = Modifier.padding(end = 32.dp),
                        ) {
                            Text(text = "キャンセル")
                        }
                        Button(
                            onClick = {
                                isEditModalVisible.value = false
                                viewModel.upsertTodo(
                                    cardItem.id,
                                    editedTitleText,
                                    editedText,
                                    dateTimeLong,
                                    cardItem.notifications,
                                    cardItem.share,
                                )
                            },
                        ) {
                            Text(text = "完了")
                        }
                    }
                }
            }
        }
    }
}
