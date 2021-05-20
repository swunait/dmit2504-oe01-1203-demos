package ca.nait.dmit.dmit2504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val todoList = mutableListOf<String>()
    var todoEditText: EditText? = null
    var todoListView: ListView? = null
    var todoDataAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the views for the EditText and ListView
        todoEditText = findViewById(R.id.todoEditText)
        todoListView = findViewById(R.id.todoListView)

        // Create the adapter for the ListView
        todoDataAdapter = ArrayAdapter(this, R.layout.list_view_item, R.id.list_item_todoTextView, todoList)
        // Set the adpater of the ListView
        todoListView?.adapter = todoDataAdapter

    }

    fun onAddTodoClick(view: View?) {
        // Retrieve the value from the Todo EditText
        val todoName = todoEditText?.text.toString()
        // Add the todoName to the todoList if it is not empty
        if (!TextUtils.isEmpty(todoName)) {
            todoList.add(todoName)
            // Clear the EditText field
            todoEditText?.setText("")
            // Notify the ListView data has changed
            todoDataAdapter?.notifyDataSetChanged()
        } else {
            // Display an error message using a Toast
            Toast.makeText(this, "Todo name is require", Toast.LENGTH_SHORT).show()
        }

    }
}