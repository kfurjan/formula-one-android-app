package hr.algebra.formula1.repository

import androidx.lifecycle.LiveData

interface Repository<T> {

    fun queryAll(): LiveData<List<T>>

    suspend fun insert(data: T)

    suspend fun update(data: T): Int

    suspend fun delete(data: T): Int
}
