package com.example.kosenstride.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kosenstride.ui.home.component.DeadlineTodo
import com.example.kosenstride.ui.home.component.TimeSchedule

data class Class(
    val day: String,
    val classList: List<String>,
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val dayClassList = listOf("1限", "2限", "3限", "4限")

    val mockClassList =
        listOf(
            Class(day = "月", classList = listOf("数学", "英語", "物理", "化学")),
            Class(day = "火", classList = listOf("国語", "歴史", "地理", "情報")),
            Class(day = "水", classList = listOf("体育", "音楽", "美術", "保健")),
            Class(day = "木", classList = listOf("数学", "英語", "物理", "化学")),
            Class(day = "金", classList = listOf("国語", "歴史", "地理", "情報")),
        )

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "グループ名", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
        TimeSchedule(dayClassList, mockClassList) // 　時間割
        if (homeUiState.todo.isNotEmpty()) DeadlineTodo(viewModel = viewModel)
    }
}
