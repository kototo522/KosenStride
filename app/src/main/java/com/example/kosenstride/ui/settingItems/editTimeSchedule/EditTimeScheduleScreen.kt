package com.example.kosenstride.ui.settingItems.editTimeSchedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.home.Class
import com.example.kosenstride.ui.settingItems.editTimeSchedule.component.ClassCard

@Composable
fun EditTimeScheduleScreen() {
    val mockClassList =
        listOf(
            Class(day = "月", classList = listOf("数学", "英語", "物理", "化学")),
            Class(day = "火", classList = listOf("国語", "歴史", "地理", "情報")),
            Class(day = "水", classList = listOf("体育", "音楽", "美術", "保健")),
            Class(day = "木", classList = listOf("数学", "英語", "物理", "化学")),
            Class(day = "金", classList = listOf("国語", "歴史", "地理", "情報")),
        )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(mockClassList.size) { index ->
            val timeSchedule = mockClassList[index]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = timeSchedule.day + "曜日",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
                timeSchedule.classList.forEach { className ->
                    ClassCard(
                        title = className,
                        onItemClick = { /* ToDo: onItemClickの処理を追加 */ }
                    )
                }
            }
        }
    }
}