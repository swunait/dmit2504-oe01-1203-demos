package ca.nait.dmit.dmit2054.exercisehockeyteamdivision

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class HockeyDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{

    object DivisionContract {
        object DivisionEntry : BaseColumns {
            const val TABLE_NAME = "Division"
            const val COLUMN_NAME_NAME = "Name"
        }
    }

    companion object {
        private const val DATABASE_NAME = "HockeyDatabase.db"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_DIVISION =
            "CREATE TABLE ${DivisionContract.DivisionEntry.TABLE_NAME}" +
                    "(" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${DivisionContract.DivisionEntry.COLUMN_NAME_NAME} TEXT " +
                    ")"
        private const val SQL_DROP_TABLE_DIVISION = "DROP TABLE IF EXISTS ${DivisionContract.DivisionEntry.TABLE_NAME} "
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_DIVISION)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_TABLE_DIVISION)
        db?.execSQL(SQL_CREATE_TABLE_DIVISION)
    }

    fun insertDivision(name: String) : Long {
        val columnValues = ContentValues().apply {
            put(DivisionContract.DivisionEntry.COLUMN_NAME_NAME, name)
        }
        val db = writableDatabase
        return db.insert(DivisionContract.DivisionEntry.TABLE_NAME, null, columnValues)
    }

    fun listDivisions(): Cursor {
        // Define the array of column names to return
        val projection = arrayOf(
            BaseColumns._ID,
            DivisionContract.DivisionEntry.COLUMN_NAME_NAME,
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
        val sortOrder = "${DivisionContract.DivisionEntry.COLUMN_NAME_NAME} "
        // Get a readable database
        val db = readableDatabase
        // Execute query and store cursor result
        val resultCursor = db.query(
            DivisionContract.DivisionEntry.TABLE_NAME,
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