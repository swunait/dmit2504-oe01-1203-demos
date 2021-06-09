package ca.nait.dmit.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

// Step 1: Create a class that inherits from SQLiteOpenHelper
class ExpenseDatabase(context: Context)
    : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

        // Step 2: Define constants for database name, database version, table name, column names
        companion object {
            private const val DATABASE_NAME = "expenses.db"
            private const val DATABASE_VERSION = 1
            const val TABLE_EXPENSE_NAME = "expenses"
            const val TABLE_EXPENSE_COLUMN_DESCRIPTION = "description"
            const val TABLE_EXPENSE_COLUMN_AMOUNT = "amount"
            const val TABLE_EXPENSE_COLUMN_DATE = "date"
        }

    // Step 3: Create the tables in onCreate() and drop the tables in onUpgrade()
    override fun onCreate(db: SQLiteDatabase?) {
        // execute SQL statements to create required database tables
        db?.execSQL("CREATE TABLE ${TABLE_EXPENSE_NAME} "
            .plus("( ${BaseColumns._ID} INTEGER PRIMARY KEY, ")
            .plus("${TABLE_EXPENSE_COLUMN_DESCRIPTION} TEXT, ")
            .plus("${TABLE_EXPENSE_COLUMN_AMOUNT} TEXT, ")
            .plus("${TABLE_EXPENSE_COLUMN_DATE} TEXT);")
        )

        db?.execSQL("INSERT INTO ${TABLE_EXPENSE_NAME} (${TABLE_EXPENSE_COLUMN_DESCRIPTION},${
            TABLE_EXPENSE_COLUMN_AMOUNT}, ${
            TABLE_EXPENSE_COLUMN_DATE}) VALUES('Item 1','1.11','2021-06-01')")
        db?.execSQL("INSERT INTO ${TABLE_EXPENSE_NAME} (${TABLE_EXPENSE_COLUMN_DESCRIPTION},${
            TABLE_EXPENSE_COLUMN_AMOUNT}, ${
            TABLE_EXPENSE_COLUMN_DATE}) VALUES('Item 2','2.22','2021-06-02')")
        db?.execSQL("INSERT INTO ${TABLE_EXPENSE_NAME} (${TABLE_EXPENSE_COLUMN_DESCRIPTION},${
            TABLE_EXPENSE_COLUMN_AMOUNT}, ${
            TABLE_EXPENSE_COLUMN_DATE}) VALUES('Item 3','3.33','2021-06-03')")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE ${TABLE_EXPENSE_NAME}")
        onCreate(db)
    }

    // Step 4: Create methods to perform database operations
    fun createExpense(description: String, amount: String, date: String) : Long {
        // Get a writeable database
        val db = writableDatabase

        // Create a ContentValue with the values to insert
//        val values = ContentValues()
//        values.put(TABLE_EXPENSE_COLUMN_DESCRIPTION, description)
//        values.put(TABLE_EXPENSE_COLUMN_AMOUNT, amount)
//        values.put(TABLE_EXPENSE_COLUMN_DATE, date)

        val values = ContentValues().apply {
            put(TABLE_EXPENSE_COLUMN_DESCRIPTION, description)
            put(TABLE_EXPENSE_COLUMN_AMOUNT, amount)
            put(TABLE_EXPENSE_COLUMN_DATE, date)
        }

        // Call the insert() method
        return db.insert(TABLE_EXPENSE_NAME, null, values)
    }

    val expenses : Cursor
        get() {
            // Get a readable database
            val db = readableDatabase
            // Construct SQL query statement
            val queryStatement = "SELECT ${BaseColumns._ID}, "
                .plus("${TABLE_EXPENSE_COLUMN_DESCRIPTION}, ")
                .plus("${TABLE_EXPENSE_COLUMN_AMOUNT}, ")
                .plus("${TABLE_EXPENSE_COLUMN_DATE} ")
                .plus(" FROM ${TABLE_EXPENSE_NAME} ")
                .plus(" ORDER BY ${TABLE_EXPENSE_COLUMN_DATE} DESC ")
            return db.rawQuery(queryStatement, null)
        }

}