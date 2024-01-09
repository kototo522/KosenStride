package com.example.kosenstride.ui.todo.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListSortButton(expanded: MutableState<Boolean>) {
    Button(
        onClick = { expanded.value = !expanded.value },
        modifier =
            Modifier
                .height(32.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowDownward,
            contentDescription = "並び替え",
            modifier = Modifier.width(20.dp),
            tint = Color.Black,
        )
        Text(text = "並び替え", fontSize = 12.sp, modifier = Modifier.height(20.dp), color = Color.Black)
    }
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
    ) {
        DropdownMenuItem(
            onClick = {
                expanded.value = !expanded.value
            },
            text = {
                Text(text = "オリジナル")
            },
        )
        DropdownMenuItem(
            onClick = {
                expanded.value = !expanded.value
            },
            text = {
                Text(text = "期限の早い順")
            },
        )
    }
}
