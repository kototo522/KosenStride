package com.example.kosenstride.ui.todo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.kosenstride.ui.todo.CardItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEditModal(
    isEditModalVisible: MutableState<Boolean>,
    cardItem: CardItem,
) {
    var editedTitleText by remember { mutableStateOf(cardItem.title) }
    var editedText by remember { mutableStateOf(cardItem.text) }
    var dateTimeText by remember { mutableStateOf(cardItem.dateTime) }

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
                    editedText?.let {
                        TextField(
                            value = it,
                            onValueChange = { text ->
                                editedText = text
                            },
                            textStyle = TextStyle(fontSize = 14.sp),
                            modifier =
                                Modifier
                                    .padding(vertical = 8.dp, horizontal = 20.dp)
                                    .fillMaxWidth(),
                        )
                    }
                    Text(text = "期限", fontSize = 14.sp, modifier = Modifier.height(20.dp))
                    dateTimeText?.let {
                        TextField(
                            value = it,
                            onValueChange = { text ->
                                dateTimeText = text
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
