package ca.nait.dmit.backgroundprocessingdemo

import JitterDatabase
import android.content.Context
import android.database.Cursor
import androidx.loader.content.CursorLoader

class JitterCursorLoader(context: Context) : CursorLoader(context) {

    private val _context: Context

    init {
        _context = context
    }

    override fun loadInBackground(): Cursor? {
        val database = JitterDatabase(_context)
        return database.getAllRecords()
    }
}