package com.example.kosenstride.ui.setting

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kosenstride.ui.setting.component.SettingCard

data class SettingItem(val title: String, val onClick: () -> Unit)

@Composable
fun SettingScreen(
    navController: NavHostController,
) {
    val settingsList = listOf(
        SettingItem("プロフィール編集") { /* Handle click */ },
        SettingItem("時間割編集[全体公開]") { navController.navigate("editTimeSchedule") },
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
                onItemClick = setting.onClick
            )
        }
    }
}
