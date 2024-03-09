package com.example.kosenstride.ui.todo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kosenstride.data.local.entities.TodoEntity
import com.example.kosenstride.ui.todo.TodoListViewModel

@Composable
fun ListCard(
    cardItem: TodoEntity,
    viewModel: TodoListViewModel,
) {
    var expanded by remember { mutableStateOf(false) }
    val menuItems = listOf("編集", "削除")
    val isEditModalVisible = rememberSaveable { mutableStateOf(false) }
    val isDeleteModalVisible = rememberSaveable { mutableStateOf(false) }

    val notificationColor =
        if (cardItem.notifications) {
            MaterialTheme.colorScheme.primary
        } else {
            Color.DarkGray
        }
    val switchShareIcon =
        if (cardItem.share) {
            Icons.Default.PeopleAlt
        } else {
            Icons.Filled.Person
        }

    Card(
        shape = RoundedCornerShape(size = 5.dp),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
        modifier =
            Modifier
                .padding(start = 8.dp, top = 4.dp, end = 8.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF215FA6),
                    shape = RoundedCornerShape(size = 5.dp),
                )
                .fillMaxWidth(),
    ) {
        Column {
            Text(
                text = cardItem.title,
                style =
                    TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF3D4758),
                        letterSpacing = 0.5.sp,
                    ),
                modifier = Modifier.padding(4.dp),
            )
            Text(
                text = cardItem.text,
                modifier = Modifier.padding(start = 20.dp, top = 4.dp),
                style =
                    TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF3D4758),
                        letterSpacing = 0.5.sp,
                    ),
            )
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                        .height(40.dp),
            ) {
                Text(
                    text = cardItem.dateTime,
                    modifier =
                        Modifier
                            .weight(4f)
                            .padding(10.dp),
                    style =
                        TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF3D4758),
                            textAlign = TextAlign.Right,
                            letterSpacing = 0.5.sp,
                        ),
                )
                IconButton(onClick = { viewModel.changeNoticeTodo(cardItem, !(cardItem.notifications)) }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "通知",
                        modifier = Modifier.width(20.dp),
                        tint = notificationColor,
                    )
                }
                Icon(
                    imageVector = switchShareIcon,
                    contentDescription = "共有",
                    modifier = Modifier.width(20.dp).padding(top = 10.dp),
                )
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "その他",
                        modifier = Modifier.width(20.dp),
                    )
                }
            }
            Box(
                modifier =
                    Modifier
                        .width(80.dp)
                        .align(Alignment.End)
                        .background(MaterialTheme.colorScheme.background),
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    menuItems.forEach { action ->
                        DropdownMenuItem(
                            text = { Text(text = action) },
                            onClick = {
                                when (action) {
                                    "編集" -> {
                                        isEditModalVisible.value = true
                                    }
                                    "削除" -> {
                                        isDeleteModalVisible.value = true
                                    }
                                }
                                expanded = false
                            }
                        )
                    }
                }
                if (isEditModalVisible.value) {
                    CardEditModal(
                        isEditModalVisible = isEditModalVisible,
                        cardItem = cardItem,
                        viewModel = viewModel,
                    )
                }
                if (isDeleteModalVisible.value) {
                    CardDeleteModal(
                        isEditModalVisible = isDeleteModalVisible,
                        id = cardItem.id,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
