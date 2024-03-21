package com.example.kosenstride.ui.home.component

import androidx.compose.runtime.Composable
import com.example.kosenstride.ui.home.Class

@Composable
fun TimeSchedule(
    dayClassList: List<String>,
    mockClassList: List<Class>,
) {
    DayOfWeekList(mockClassList.map { it.day }) // 月火水木金
    DayClassList(dayClassList, mockClassList)
}
