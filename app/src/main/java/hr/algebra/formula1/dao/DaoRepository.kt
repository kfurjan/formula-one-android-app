package hr.algebra.formula1.dao

interface DaoRepository <T> {

    suspend fun insert(data: T): Long

    suspend fun query(): List<T>

    suspend fun queryById(id: Long): T

    suspend fun delete(): Int

    suspend fun deleteById(id: Long): Int

    suspend fun update(data: T): Int
}
