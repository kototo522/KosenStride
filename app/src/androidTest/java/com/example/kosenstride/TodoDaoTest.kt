package com.example.kosenstride

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.kosenstride.data.local.AppDatabase
import com.example.kosenstride.data.local.entities.TodoEntity
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class TodoDaoTest {
    private lateinit var database: AppDatabase
    private val todo1 =
        TodoEntity(
            id = "id1",
            title = "数学",
            text = "プリント１まい",
            dateTime = 1_000_000_000L,
            notifications = false,
            share = false,
            latistDay = "2021-08-01 00:00:00",
        )
    private val todo2 =
        TodoEntity(
            id = "id2",
            title = "国語",
            text = "音読",
            dateTime = 1_000_000_000L,
            notifications = true,
            share = false,
            latistDay = "2021-08-01 00:00:00",
        )

    @Before
    fun initDb() {
        database =
            Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                AppDatabase::class.java,
            ).allowMainThreadQueries().build()
    }

    @Test
    fun upsertTodoAndGetById() =
        runTest {
            database.todoDao().upsert(todo1)
            val loaded = database.todoDao().getById(todo1.id)

            TestCase.assertNotNull(loaded as TodoEntity)
            assertEquals(todo1.id, loaded.id)
            assertEquals(todo1.title, loaded.title)
            assertEquals(todo1.text, loaded.text)
            assertEquals(todo1.dateTime, loaded.dateTime)
            assertEquals(todo1.notifications, loaded.notifications)
            assertEquals(todo1.share, loaded.share)
            assertEquals(todo1.latistDay, loaded.latistDay)
        }

    @Test
    fun upsertTodoReplacesOnConflict() =
        runTest {
            database.todoDao().deleteAll()
            database.todoDao().upsert(todo1)
            val loaded = database.todoDao().getById(todo1.id)

            assertEquals(todo1.id, loaded?.id)
            assertEquals(todo1.title, loaded?.title)
            assertEquals(todo1.text, loaded?.text)
            assertEquals(todo1.dateTime, loaded?.dateTime)
            assertEquals(todo1.notifications, loaded?.notifications)
            assertEquals(todo1.share, loaded?.share)
            assertEquals(todo1.latistDay, loaded?.latistDay)
        }

    @Test
    fun observeAllTodos() =
        runTest {
            database.todoDao().deleteAll()
            database.todoDao().upsertAll(listOf(todo1, todo2))
            val todos = database.todoDao().observeAll().first()
            assertEquals(2, todos.size)
            assertTrue(todos.contains(todo1))
            assertTrue(todos.contains(todo2))
    }

    @Test
    fun observeTodoById() =
        runTest {
            database.todoDao().upsert(todo1)
            val observedTodo = database.todoDao().observeById(todo1.id).first()
            assertEquals(todo1, observedTodo)
        }

    @Test
    fun deleteTodoById() =
        runTest {
            database.todoDao().upsert(todo1)
            database.todoDao().deleteById(todo1.id)
            val deletedTodo = database.todoDao().getById(todo1.id)
            assertNull(deletedTodo)
        }

    @Test
    fun deleteAllTodos() =
        runTest {
            database.todoDao().upsertAll(listOf(todo1, todo2))
            database.todoDao().deleteAll()
            val todos = database.todoDao().getAll()
            assertTrue(todos.isEmpty())
        }
}
