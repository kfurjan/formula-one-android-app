package hr.algebra.formula1

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import hr.algebra.formula1.dao.Formula1Database
import hr.algebra.formula1.model.Season

private const val WRONG_URI = "Wrong URI"
private const val AUTHORITY = "hr.algebra.formula1.season_provider"
private const val PATH = "seasons"
val SEASON_CONTENT_PROVIDER_URI: Uri = Uri.parse("content://$AUTHORITY/$PATH")
private const val SEASONS = 10
private const val SEASON_ID = 20

private val URI_MATCHER = with(UriMatcher(UriMatcher.NO_MATCH)) {
    addURI(AUTHORITY, PATH, SEASONS)
    addURI(AUTHORITY, "$PATH/#", SEASON_ID)
    this
}

private const val CONTENT_DIR_TYPE =
    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

private const val CONTENT_ITEM_TYPE =
    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH

class SeasonContentProvider : ContentProvider() {

    private lateinit var formula1Database: Formula1Database

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        when (URI_MATCHER.match(uri)) {
            SEASONS -> return formula1Database.seasonDao().delete()
            SEASON_ID -> {
                val id = uri.lastPathSegment
                if (id != null) {
                    return formula1Database.seasonDao().deleteById(id.toLong())
                }
            }
        }
        throw IllegalArgumentException(WRONG_URI)
    }

    override fun getType(uri: Uri): String? {
        when (URI_MATCHER.match(uri)) {
            SEASONS -> return CONTENT_DIR_TYPE
            SEASON_ID -> return CONTENT_ITEM_TYPE
        }
        throw IllegalArgumentException("Wrong URI")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val id = formula1Database.seasonDao().insert(Season.fromContentValues(values))
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
            SEASONS -> return formula1Database.seasonDao().query()
            SEASON_ID -> {
                val id = uri.lastPathSegment
                if (id != null) {
                    return formula1Database.seasonDao().queryById(id.toLong())
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
            return formula1Database.seasonDao().update(Season.fromContentValues(values))
        }
        throw IllegalArgumentException(WRONG_URI)
    }
}
