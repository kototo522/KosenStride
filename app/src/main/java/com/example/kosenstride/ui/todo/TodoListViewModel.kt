package com.example.kosenstride.ui.todo

import android.util.Log
import com.example.kosenstride.data.local.dao.TodoDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kosenstride.data.local.entities.TodoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(

    private val todoDao: TodoDao,

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAllTodo()
    }

    fun observeAllTodo() =
        viewModelScope.launch {
            val todos = todoDao.observeAll()
            todos.collect {
                _uiState.value = TodoUiState(todo = it)
            }
        }

    fun observeTodo(id: String) =
        viewModelScope.launch {
            todoDao.observeById(id)
        }

    fun upsert(todo: TodoEntity) = viewModelScope.launch {
        todoDao.upsert(todo)
    }

    fun upsertAll(todos: List<TodoEntity>) = viewModelScope.launch {
        todoDao.upsertAll(todos)
    }

    fun deleteById(id: String) = viewModelScope.launch {
        todoDao.deleteById(id)
    }

    fun deleteAll() = viewModelScope.launch {
        todoDao.deleteAll()
    }

    fun upsertTodo(id: String, title: String, text: String, dateTime: String, notification: Boolean, share: Boolean) {
        viewModelScope.launch {
            todoDao.upsert(
                TodoEntity(
                    id = id,
                    title = title,
                    text = text,
                    dateTime = dateTime,
                    notifications = notification,
                    share = share,
                )
            )
        }
    }
    fun changeNoticeTodo(todo: TodoEntity, notification: Boolean) {
        viewModelScope.launch {
            todoDao.upsert(
                TodoEntity(
                    id = todo.id,
                    title = todo.title,
                    text = todo.text,
                    dateTime = todo.dateTime,
                    notifications = notification,
                    share = todo.share,
                )
            )
        }
    }

    }

data class TodoUiState(
    val todo: List<TodoEntity> = emptyList(),
)
