package com.example.kosenstride.ui.home.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kosenstride.data.local.entities.TodoEntity
import com.example.kosenstride.ui.createTodo.component.formatToCustomDate
import com.example.kosenstride.ui.home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DeadlineTodoCard(
    cardItem: TodoEntity,
    viewModel: HomeViewModel,
) {

    val notificationColor =
        if (cardItem.notifications) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondary
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
                    color = MaterialTheme.colorScheme.error,
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
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                    text = cardItem.dateTime.formatToCustomDate(),
                    modifier =
                        Modifier
                            .weight(4f)
                            .padding(10.dp),
                    style =
                        TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            fontWeight = FontWeight(700),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
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
                    modifier = Modifier
                        .width(20.dp)
                        .padding(top = 10.dp),
                )
                Spacer(modifier = Modifier.width(30.dp))
            }
        }
    }
}
