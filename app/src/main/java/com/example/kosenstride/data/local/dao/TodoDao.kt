package com.example.kosenstride.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.kosenstride.data.local.entities.TodoEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun observeAll(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun observeById(id: String): Flow<TodoEntity>

    @Query("SELECT * FROM todo")
    suspend fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getById(id: String): TodoEntity?

    @Upsert
    suspend fun upsert(todo: TodoEntity)

    @Upsert
    suspend fun upsertAll(todo: List<TodoEntity>)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM todo")
    suspend fun deleteAll()
}
