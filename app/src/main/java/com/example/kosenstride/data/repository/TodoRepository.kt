package com.example.kosenstride.data.repository

import com.example.kosenstride.data.local.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun observeAll(): Flow<List<TodoEntity>>
    fun observeById(id: String): Flow<TodoEntity>
    suspend fun getAll(): List<TodoEntity>
    suspend fun getById(id: String)
    suspend fun upsert(todo: TodoEntity)

    suspend fun upsertAll(todo: List<TodoEntity>)
    suspend fun deleteById(id: String)
    suspend fun deleteAll()
}
