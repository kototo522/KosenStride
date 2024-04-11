package com.example.kosenstride.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class SubjectEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
)
