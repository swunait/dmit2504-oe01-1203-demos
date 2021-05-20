package ca.nait.dmit.dmit2504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*

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

        // Set and handle the onItemClick event of the ListView
        todoListView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val toastMessage = "Selected ${todoList[position]}"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }

        // Set and handle the onItemLongClick event of the ListView
        todoListView?.onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
            // Remove the item at the selected position
            todoList.removeAt(position)
            // Notify the list view that data has been changed
            todoDataAdapter?.notifyDataSetChanged()
            // return true to indicate event has been processed
            true
        }

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
            Toast.makeText(this, "Todo name is required", Toast.LENGTH_SHORT).show()
        }

    }
}