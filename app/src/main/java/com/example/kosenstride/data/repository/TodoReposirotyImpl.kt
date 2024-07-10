package com.example.kosenstride.data.repository

import com.example.kosenstride.data.local.dao.TodoDao
import com.example.kosenstride.data.local.entities.TodoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {
    override fun observeAll(): Flow<List<TodoEntity>> {
        return todoDao.observeAll()
    }

    override fun observeById(id: String): Flow<TodoEntity> {
        return todoDao.observeById(id)
    }

    override suspend fun getAll(): List<TodoEntity> {
        return withContext(Dispatchers.IO) {
            todoDao.getAll()
        }
    }

    override suspend fun getById(id: String) {
        return withContext(Dispatchers.IO) {
            todoDao.getById(id)
        }
    }

    override suspend fun upsert(todo: TodoEntity) {
        withContext(Dispatchers.IO) {
            todoDao.upsert(todo)
        }
    }

    override suspend fun upsertAll(todos: List<TodoEntity>) {
        withContext(Dispatchers.IO) {
            todoDao.upsertAll(todos)
        }
    }

    override suspend fun deleteById(id: String) {
        withContext(Dispatchers.IO) {
            todoDao.deleteById(id)
        }
    }

    override suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            todoDao.deleteAll()
        }
    }
}