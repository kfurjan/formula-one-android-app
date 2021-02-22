package hr.kfurjan.formula1.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {

    fun queryAll(): Flow<List<T>>

    suspend fun insert(data: T)

    suspend fun update(data: T): Int

    suspend fun delete(data: T): Int
}
