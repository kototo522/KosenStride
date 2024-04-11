package com.example.kosenstride.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.kosenstride.data.local.entities.SubjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject")
    fun observeAll(): Flow<List<SubjectEntity>>

    @Query("SELECT * FROM subject WHERE id = :id")
    fun observeById(id: String): Flow<SubjectEntity>

    @Query("SELECT * FROM subject")
    suspend fun getAll(): List<SubjectEntity>

    @Query("SELECT * FROM subject WHERE id = :id")
    suspend fun getById(id: String): SubjectEntity?

    @Upsert
    suspend fun upsert(subject: SubjectEntity)

    @Upsert
    suspend fun upsertAll(subject: List<SubjectEntity>)

    @Query("DELETE FROM subject WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM subject")
    suspend fun deleteAll()
}
