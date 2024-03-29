package com.example.kosenstride.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kosenstride.ui.home.Class

@Composable
fun DayClassList(
    dayClassList: List<String>,
    mockClassList: List<Class>,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            dayClassList.forEach { day ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier =
                        Modifier
                            .width(50.dp)
                            .height(60.dp)
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(size = 5.dp),
                            ),
                ) {
                    Text(text = day, color = MaterialTheme.colorScheme.background)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            mockClassList.forEachIndexed { index, classData ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    classData.classList.forEachIndexed { classIndex, className ->
                        val boxColor =
                            if (index % 2 == 0 && classIndex % 2 == 0 || (index % 2 == 1 && classIndex % 2 == 1)) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                            }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier =
                                Modifier
                                    .width(50.dp)
                                    .height(60.dp)
                                    .background(
                                        color = boxColor,
                                        shape = RoundedCornerShape(size = 5.dp),
                                    ),
                        ) {
                            Text(text = className)
                        }
                    }
                }
            }
        }
    }
}
