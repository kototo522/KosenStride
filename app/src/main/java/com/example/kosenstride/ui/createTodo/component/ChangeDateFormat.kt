package com.example.kosenstride.ui.createTodo.component

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.formatToCustomDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 (E) HH時mm分", Locale.JAPANESE)
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun Long.formatToCustomDate(): String {
    val instant = Instant.ofEpochMilli(this)
    val zoneId = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 (E) HH時mm分", Locale.JAPANESE)
    return formatter.format(instant.atZone(zoneId))
}
