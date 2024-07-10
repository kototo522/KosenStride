package com.example.kosenstride.ui.createTodo

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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateTodoViewModel
@Inject
constructor(
    private val todoRepository: TodoRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState = _uiState.asStateFlow()

    fun observeAllToto() =
        viewModelScope.launch {
            todoRepository.observeAll()
        }

    fun observeTodo(id: String) =
        viewModelScope.launch {
            todoRepository.observeById(id)
        }

    fun getAllTodo() =
        viewModelScope.launch {
            todoRepository.getAll()
        }

    fun getTodoById(id: String) =
        viewModelScope.launch {
            todoRepository.getById(id)
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
        title: String,
        text: String,
        date: LocalDate,
        time: LocalTime,
        share: Boolean,
    ) {
        val localDateTime = LocalDateTime.of(date, time)
        val dateTimeInstant = ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant()

        viewModelScope.launch {
            todoRepository.upsert(
                TodoEntity(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    text = text,
                    dateTime = dateTimeInstant.toEpochMilli(),
                    notifications = true,
                    share = share,
                    latistDay = LocalDateTime.now().toString(),
                ),
            )
        }
    }
}

data class TodoUiState(
    val todo: List<TodoEntity> = emptyList(),
)
