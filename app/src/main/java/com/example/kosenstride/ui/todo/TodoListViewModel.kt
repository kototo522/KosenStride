package com.example.kosenstride.ui.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kosenstride.data.local.entities.TodoEntity
import com.example.kosenstride.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel
@Inject
constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAllTodo()
    }

    fun observeAllTodo() =
        viewModelScope.launch {
            val todos = todoRepository.observeAll()
            todos.collect {
                _uiState.value = TodoUiState(todo = it)
            }
        }

    fun observeTodo(id: String) =
        viewModelScope.launch {
            todoRepository.observeById(id)
        }

    fun upsert(todo: TodoEntity) =
        viewModelScope.launch {
            todoRepository.upsert(todo)
        }

    fun upsertAll(todos: List<TodoEntity>) =
        viewModelScope.launch {
            todoRepository.upsertAll(todos)
        }

    fun deleteById(id: String) =
        viewModelScope.launch {
            todoRepository.deleteById(id)
        }

    fun deleteAll() =
        viewModelScope.launch {
            todoRepository.deleteAll()
        }

    @RequiresApi(Build.VERSION_CODES.O)
    fun upsertTodo(
        id: String,
        title: String,
        text: String,
        dateTime: Long,
        notification: Boolean,
        share: Boolean,
    ) {
        viewModelScope.launch {
            todoRepository.upsert(
                TodoEntity(
                    id = id,
                    title = title,
                    text = text,
                    dateTime = dateTime,
                    notifications = notification,
                    share = share,
                    latistDay = LocalDateTime.now().toString(),
                ),
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeNoticeTodo(
        todo: TodoEntity,
        notification: Boolean,
    ) {
        viewModelScope.launch {
            todoRepository.upsert(
                TodoEntity(
                    id = todo.id,
                    title = todo.title,
                    text = todo.text,
                    dateTime = todo.dateTime,
                    notifications = notification,
                    share = todo.share,
                    latistDay = LocalDateTime.now().toString(),
                ),
            )
        }
    }
}

data class TodoUiState(
    val todo: List<TodoEntity> = emptyList(),
)
