package com.example.kosenstride.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kosenstride.data.local.dao.SubjectDao
import com.example.kosenstride.data.local.dao.TodoDao
import com.example.kosenstride.data.local.entities.SubjectEntity
import com.example.kosenstride.data.local.entities.TodoEntity

@Database(entities = [TodoEntity::class, SubjectEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun subjectDao(): SubjectDao
}
