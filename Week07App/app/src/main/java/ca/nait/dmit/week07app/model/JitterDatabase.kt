package ca.nait.dmit.week07app.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class JitterDatabase(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "JittersDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TEXT_TYPE = " TEXT"
        private const val COMMA_SEPARATOR = ","

        private const val SQL_CREATE_TABLE_JITTER =
            "CREATE TABLE ${JitterDatabaseContract.JitterEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY " + COMMA_SEPARATOR +
            "${JitterDatabaseContract.JitterEntry.COLUMN_NAME_LOGINNAME}" + TEXT_TYPE + COMMA_SEPARATOR +
            "${JitterDatabaseContract.JitterEntry.COLUMN_NAME_MESSAGE}" + TEXT_TYPE + COMMA_SEPARATOR +
            "${JitterDatabaseContract.JitterEntry.COLUMN_NAME_DATE}" + TEXT_TYPE + ")"

        private const val SQL_DROP_TABLE_JITTER =
            "DROP TABLE IF EXISTS ${JitterDatabaseContract.JitterEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_JITTER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_TABLE_JITTER)
        db?.execSQL(SQL_CREATE_TABLE_JITTER)
    }

    fun insertRecord(jitterId: Int, loginName: String, message: String, dateCreated: String) : Long {
        val columnValues = ContentValues().apply {
            put(JitterDatabaseContract.JitterEntry.COLUMN_NAME_LOGINNAME, loginName)
            put(JitterDatabaseContract.JitterEntry.COLUMN_NAME_MESSAGE, message)
            put(JitterDatabaseContract.JitterEntry.COLUMN_NAME_DATE, dateCreated)
            put(BaseColumns._ID, jitterId)
        }
        // Get a writeable database
        val db = writableDatabase
        return db.insert(JitterDatabaseContract.JitterEntry.TABLE_NAME, null,columnValues)
    }

    fun getAllRecords() : Cursor {
        // Define the array of column names to return
        val projection = arrayOf(
            BaseColumns._ID,
            JitterDatabaseContract.JitterEntry.COLUMN_NAME_LOGINNAME,
            JitterDatabaseContract.JitterEntry.COLUMN_NAME_MESSAGE,
            JitterDatabaseContract.JitterEntry.COLUMN_NAME_DATE
        )
        // Define the WHERE clause
        val selection: String? = null
        // Define the parameter values for the WHERE clause
        val selectionArgs: Array<String>? = null
        // Define the GROUP BY clause
        val groupBy: String? = null
        // Define the HAVING clause
        val having: String? = null
        // Define ORDER BY clause
        val sortOrder = "${JitterDatabaseContract.JitterEntry.COLUMN_NAME_DATE} DESC"
        // Get a readable database
        val db = readableDatabase
        // Execute query and store cursor result
        val resultCursor = db.query(
            JitterDatabaseContract.JitterEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            groupBy,
            having,
            sortOrder
        )
        return resultCursor
    }
}