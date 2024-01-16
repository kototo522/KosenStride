package com.example.kosenstride.ui.createTodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kosenstride.data.local.dao.TodoDao
import com.example.kosenstride.data.local.entities.TodoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateTodoViewModel @Inject constructor(

    private val todoDao: TodoDao,

    ) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.asStateFlow()

    fun observeAllToto() =
        viewModelScope.launch {
            todoDao.observeAll()
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

    fun addTodo(title: String, text: String, dateTime: String, share: Boolean) {
        viewModelScope.launch {
            todoDao.upsert(
                TodoEntity(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    text = text,
                    dateTime = dateTime,
                    notifications = true,
                    share = share,
                )
            )
        }
    } }

data class TodoUiState(
    val todo: List<TodoEntity> = emptyList(),
)
