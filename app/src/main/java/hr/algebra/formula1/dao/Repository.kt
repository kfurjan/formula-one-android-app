package hr.algebra.formula1.dao

import android.database.Cursor

interface Repository <T> {

    fun insert(data: T?): Long

    fun query(): Cursor?

    fun queryById(id: Long): Cursor?

    fun delete(): Int

    fun deleteById(id: Long): Int

    fun update(data: T?): Int
}
