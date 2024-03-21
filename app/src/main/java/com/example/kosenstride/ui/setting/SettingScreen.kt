package com.example.kosenstride.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.setting.component.SettingCard

data class SettingItem(val title: String, val onClick: () -> Unit)

@Composable
fun SettingScreen() {
    val settingsList = listOf(
        SettingItem("プロフィール編集") { /* Handle click */ },
        SettingItem("プロフィール編集") { /* Handle click */ },
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        items(settingsList.size) { index ->
            val setting = settingsList[index]
            SettingCard(
                title = setting.title,
                onItemClick = setting.onClick)
        }
    }
}

@Preview
@Composable
fun PreviewSettingScreen() {
    SettingScreen()
}