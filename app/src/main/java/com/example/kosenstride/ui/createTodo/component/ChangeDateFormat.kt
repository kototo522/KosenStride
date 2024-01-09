package com.example.kosenstride.ui.createTodo.component

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun ChangeDateFormat(date: Date): String {
    val formatter = SimpleDateFormat("yyyy年MM月dd日 (E)", Locale.JAPANESE)
    return formatter.format(date)
}
