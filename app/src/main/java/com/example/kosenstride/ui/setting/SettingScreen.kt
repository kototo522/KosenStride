package com.example.kosenstride.ui.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.setting.component.SettingCard

data class SettingItem(val title: String, val text: String, val onClick: () -> Unit)

@Composable
fun SettingScreen() {
    val settingsList = listOf(
        SettingItem("General", "Configure general settings") { /* Handle click */ },
        SettingItem("Notifications", "Configure notification settings") { /* Handle click */ },
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
                text = setting.text,
                onItemClick = setting.onClick)
        }
    }
}

@Preview
@Composable
fun PreviewSettingScreen() {
    SettingScreen()
}