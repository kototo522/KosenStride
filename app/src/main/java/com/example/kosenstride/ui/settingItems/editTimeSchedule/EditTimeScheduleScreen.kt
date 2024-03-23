package com.example.kosenstride.ui.settingItems.editTimeSchedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.home.Class
import com.example.kosenstride.ui.settingItems.editTimeSchedule.component.ClassCard
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorder
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import java.nio.file.Files.move

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTimeScheduleScreen() {
    val dayList = listOf("月", "火", "水", "木", "金")
    val mockClassList =
        listOf("数学", "英語", "物理", "化学", "国語", "歴史", "地理", "情報", "体育", "音楽", "美術", "保健", "数学", "英語", "物理", "化学", "国語", "歴史", "地理", "情報")
    val data = remember { mutableStateOf(mockClassList) }
    val state = rememberReorderableLazyListState(
        onMove = { from, to ->
            data.value = data.value.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
        }
    )
    LazyColumn(
        state = state.listState,
        modifier = Modifier
            .reorderable(state)
            .detectReorderAfterLongPress(state)
            .fillMaxSize()
            .padding(top = 4.dp, start = 16.dp)
    ) {
        items(data.value.size) { index ->
            val item = data.value[index]
            if(index % 4 == 0){
                Text(
                    text = dayList[index / 3] + "曜日",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp),
                )
            }
            ReorderableItem(
                reorderableState = state,
                key = item,
            ) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 8.dp else 0.dp, label = "")
                Column(
                    modifier = Modifier
                        .shadow(elevation.value)
                        .fillMaxWidth()
                        .detectReorder(state)
                        .clickable {  }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    ClassCard(
                        title = data.value[index],
                        onItemClick = { /* ToDo: onItemClickの処理を追加 */ },
                    )
                }
            }
        }
    }
}