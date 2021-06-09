package ca.nait.dmit.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val expenseDatabase = ExpenseDatabase(this)

    private val descriptionEditText : EditText by lazy { findViewById(R.id.activity_main_description_edittext) }
    private val amountEditText : EditText by lazy { findViewById(R.id.activity_main_amount_edittext) }
    private val dateEditText : EditText by lazy { findViewById(R.id.activity_main_date_edittext) }
    private val expensesListView: ListView by lazy { findViewById(R.id.activity_main_expenses_listview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Toast.makeText(this,"${expenseDatabase.expenses.count} records in db", Toast.LENGTH_LONG).show()
        rebindExpensesListView()
    }

    fun onAddExpenseClick(view: View) {
        val description = descriptionEditText.text.toString()
        val amount = amountEditText.text.toString()
        val date = dateEditText.text.toString()
        val expenseId = expenseDatabase.createExpense(description, amount, date)
        Toast.makeText(this, "Created expense id ${expenseId}", Toast.LENGTH_SHORT).show()
        rebindExpensesListView()
    }

    fun rebindExpensesListView() {
        // Get a cursor of all the expenses
        val expensesCursor = expenseDatabase.expenses
        // Define an array of column names used by the cursor
        val fromColumnNames = arrayOf("_id","description","amount","date")
        // Define an array of resource ids in the listview item layout
        val toViewResourceIds = intArrayOf(R.id.list_item_expenses_id, R.id.list_item_expenses_description, R.id.list_item_expenses_amount, R.id.list_item_expenses_date)
        // Create a SimpleCursorAdapter for the ListView
        val expensesCursorAdapter = SimpleCursorAdapter(this, R.layout.list_item_expenses, expensesCursor, fromColumnNames, toViewResourceIds, 0)
        // Set the adapter for the ListView
        expensesListView.adapter = expensesCursorAdapter
    }
}