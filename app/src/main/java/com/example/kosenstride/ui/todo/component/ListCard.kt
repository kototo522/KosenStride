package com.example.kosenstride.ui.todo.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.todo.CardItem

@Composable
fun ListCard(index: Int, cardItem: CardItem, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${index+1}. ${cardItem.title}",
                    modifier = Modifier
                        .padding(
                            horizontal = 12.dp, vertical = 8.dp
                        )
                        .weight(5f),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(18.dp)
                        .weight(1f),
                ) {
                }
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "2023/8/20/17:00",
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(2f),
                    style = MaterialTheme.typography.bodySmall,
                )
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "通知",
                        modifier = modifier
                            .weight(1f)
                            .width(20.dp),
                    )
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "共有",
                        modifier = modifier
                            .weight(1f)
                            .width(20.dp),
                    )
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "その他",
                        modifier = modifier
                            .weight(1f)
                            .width(20.dp),
                    )
                }
            }
            Divider(color = Color.Gray)
        }
    }
}