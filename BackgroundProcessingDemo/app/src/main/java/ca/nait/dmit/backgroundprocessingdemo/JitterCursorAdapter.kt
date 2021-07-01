package ca.nait.dmit.backgroundprocessingdemo

import JitterDatabase
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.TextView

class JitterCursorAdapter(context: Context, cursor: Cursor, flags: Int ) : CursorAdapter(context,cursor,flags) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.list_item_jitter, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {

        val idTextView : TextView = view?.findViewById(R.id.list_item_id)!!
        val loginNameTextView : TextView = view?.findViewById(R.id.list_item_loginname)!!
        val messageTextView : TextView = view?.findViewById(R.id.list_item_message)!!
        val dateTextView : TextView = view?.findViewById(R.id.list_item_date)!!
        val deleteButton : Button = view?.findViewById(R.id.list_item_delete_button)

        val currentId : Int = cursor?.getInt(cursor?.getColumnIndexOrThrow(BaseColumns._ID)) ?: 0
        val currentLoginName : String = cursor?.getString(cursor?.getColumnIndexOrThrow(JitterDatabaseContract.JitterEntry.COLUMN_NAME_LOGINNAME)) ?: ""
        val currentMessage : String = cursor?.getString(cursor?.getColumnIndexOrThrow(JitterDatabaseContract.JitterEntry.COLUMN_NAME_MESSAGE)) ?: ""
        val currentDate : String = cursor?.getString(cursor?.getColumnIndexOrThrow(JitterDatabaseContract.JitterEntry.COLUMN_NAME_DATE)) ?: ""

        idTextView.setText("$currentId")
        loginNameTextView.setText(currentLoginName)
        messageTextView.setText(currentMessage)
        dateTextView.setText(currentDate)

        deleteButton.setOnClickListener {
            val database = JitterDatabase(context!!)
            database.deleteRecord(currentId)

            Intent().also { dataChangedIntent ->
                dataChangedIntent.setAction(INTENT_ACTION_DATA_CHANGED)
                dataChangedIntent.putExtra(EXTRA_EDIT_ID, currentId)
                context.sendBroadcast(dataChangedIntent)
            }

        }
    }

}