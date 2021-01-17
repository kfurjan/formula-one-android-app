package hr.algebra.formula1

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.model.Circuit

private const val WRONG_URI = "Wrong URI"
private const val AUTHORITY = "hr.algebra.formula1.circuit_provider"
private const val PATH = "circuits"
val CIRCUIT_CONTENT_PROVIDER_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")
private const val CIRCUITS = 10
private const val CIRCUIT_ID = 20

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, CIRCUITS)
    addURI(AUTHORITY, "$PATH/#", CIRCUIT_ID)
    this
}

private const val CONTENT_DIR_TYPE =
    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

private const val CONTENT_ITEM_TYPE =
    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

class CircuitContentProvider : ContentProvider() {

    private lateinit var formula1Database: Formula1Database

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when (URI_MATCHER.match(uri)) {
            CIRCUITS -> return formula1Database.circuitDao().delete()
            CIRCUIT_ID -> {
                val id = uri.lastPathSegment
                if (id != null) {
                    return formula1Database.circuitDao().deleteById(id.toLong())
                }
            }
        }
        throw IllegalArgumentException(WRONG_URI)
    }

    override fun getType(uri: Uri): String? {
        when (URI_MATCHER.match(uri)) {
            CIRCUITS -> return CONTENT_DIR_TYPE
            CIRCUIT_ID -> return CONTENT_ITEM_TYPE
        }
        throw IllegalArgumentException("Wrong URI")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = formula1Database.circuitDao().insert(Circuit.fromContentValues(values))
        return ContentUris.withAppendedId(DRIVER_CONTENT_PROVIDER_URI, id)
    }

    override fun onCreate(): Boolean {
        formula1Database = context?.let { Formula1Database.getInstance(it) }!!
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        when (URI_MATCHER.match(uri)) {
            CIRCUITS -> return formula1Database.circuitDao().query()
            CIRCUIT_ID -> {
                val id = uri.lastPathSegment
                if (id != null) {
                    return formula1Database.circuitDao().queryById(id.toLong())
                }
            }
        }
        throw IllegalArgumentException(WRONG_URI)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val id = uri.lastPathSegment
        if (id != null) {
            return formula1Database.circuitDao().update(Circuit.fromContentValues(values))
        }
        throw IllegalArgumentException(WRONG_URI)
    }
}
