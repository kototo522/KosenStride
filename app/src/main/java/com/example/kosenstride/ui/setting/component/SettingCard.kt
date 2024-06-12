package com.example.kosenstride.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingCard(title: String, onItemClick: () -> Unit) {
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .drawBehind {
                drawLine(
                    color = borderColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx(),
                )
            }
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
        )
    }
}

@Preview
@Composable
fun PreviewSettingCard() {
    SettingCard(title = "title", onItemClick = {})
}
