package com.example.kosenstride.ui.todo.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.sp

@Composable
fun CardDeleteModal(isEditModalVisible: MutableState<Boolean>) {
    Column {
        AlertDialog(
            onDismissRequest = {
                isEditModalVisible.value = false
            },
            title = {
                Text(text = "本当に削除しますか？", fontSize = 18.sp)
            },
            confirmButton = {
                Button(
                    onClick = {
                        isEditModalVisible.value = false
                    },
                ) {
                    Text(text = "削除")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        isEditModalVisible.value = false
                    },
                ) {
                    Text(text = "キャンセル")
                }
            },
        )
    }
}
