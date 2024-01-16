package com.example.kosenstride

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.kosenstride.data.local.AppDatabase
import com.example.kosenstride.data.local.entities.TodoEntity
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class TodoDaoTest {
    private lateinit var database: AppDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()
    }

    @Test
    fun upsertTodoAndGetById() = runTest {
        val todo = TodoEntity(
            id = "id",
            title = "数学",
            text = "プリント１まい",
            dateTime = "2021-08-01 00:00:00",
            notifications = false,
            share = false,
            latistDay = "2021-08-01 00:00:00",
        )
        database.todoDao().upsert(todo)

        val loaded = database.todoDao().getById(todo.id)

        TestCase.assertNotNull(loaded as TodoEntity)
        assertEquals(todo.id, loaded.id)
        assertEquals(todo.title, loaded.title)
        assertEquals(todo.text, loaded.text)
        assertEquals(todo.dateTime, loaded.dateTime)
        assertEquals(todo.notifications, loaded.notifications)
        assertEquals(todo.share, loaded.share)
        assertEquals(todo.latistDay, loaded.latistDay)
    }

    @Test
    fun upsertTodoReplacesOnConflict() = runTest {
        val todo = TodoEntity(
            id = "id",
            title = "数学",
            text = "プリント１まい",
            dateTime = "2021-08-01 00:00:00",
            notifications = false,
            share = false,
            latistDay = "2021-08-01 00:00:00",
        )
        database.todoDao().upsert(todo)

        val newTodo = TodoEntity(
            id = "id",
            title = "国語",
            text = "音読",
            dateTime = "2021-08-01 00:00:00",
            notifications = true,
            share = false,
            latistDay = "2021-08-01 00:00:00",
        )

        database.todoDao().upsert(newTodo)

        val loaded = database.todoDao().getById(todo.id)

        assertEquals(todo.id, loaded?.id)
        assertEquals(newTodo.title, loaded?.title)
        assertEquals(newTodo.text, loaded?.text)
        assertEquals(newTodo.dateTime, loaded?.dateTime)
        assertEquals(newTodo.notifications, loaded?.notifications)
        assertEquals(newTodo.share, loaded?.share)
        assertEquals(newTodo.latistDay, loaded?.latistDay)
    }

}