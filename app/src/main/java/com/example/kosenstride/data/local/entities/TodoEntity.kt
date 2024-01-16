package com.example.kosenstride.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "dateTime") val dateTime: String,
    @ColumnInfo(name = "notifications") var notifications: Boolean,
    @ColumnInfo(name = "share") var share: Boolean,
    @ColumnInfo(name = "latistDay") var latistDay: String,
)
