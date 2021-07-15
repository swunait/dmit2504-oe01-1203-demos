package ca.nait.dmit.sqlitedemo

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private val expenseDatabase = ExpenseDatabase(this)

    override fun onDestroy() {
        expenseDatabase.close()
        super.onDestroy()
    }

    private val descriptionEditText : EditText by lazy { findViewById(R.id.activity_main_description_edittext) }
    private val amountEditText : EditText by lazy { findViewById(R.id.activity_main_amount_edittext) }
    private val dateEditText : EditText by lazy { findViewById(R.id.activity_main_date_edittext) }
    private val expensesListView: ListView by lazy { findViewById(R.id.activity_main_expenses_listview) }

    private var editMode = false
    private var editId = 0L

    private val addButton : Button by lazy { findViewById(R.id.activity_main_add_button) }
    private val updateButton : Button by lazy { findViewById(R.id.activity_main_update_button) }
    private val deleteButton : Button by lazy { findViewById(R.id.activity_main_delete_button) }
    private val cancelButton: Button by lazy { findViewById(R.id.activity_main_cancel_Button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the update and cancel button
        updateButton.visibility = View.GONE
        deleteButton.visibility = View.GONE
        cancelButton.visibility = View.GONE


        // Allow the user to edit an item when an item is clicked on in the list view
        expensesListView.setOnItemClickListener(AdapterView.OnItemClickListener {
            parent, view, position, id ->

            editMode = true
            editId = id

            val selectedExpense = expenseDatabase.findOneExpense(id)
            if (selectedExpense != null) {
                descriptionEditText.setText(selectedExpense.description)
                amountEditText.setText(selectedExpense.amount)
                dateEditText.setText(selectedExpense.date)
            }

            updateButton.visibility = View.VISIBLE
            deleteButton.visibility = View.VISIBLE
            cancelButton.visibility = View.VISIBLE
            addButton.visibility = View.GONE

        })

//        Toast.makeText(this,"${expenseDatabase.expenses.count} records in db", Toast.LENGTH_LONG).show()
        rebindExpensesListView()
    }

    fun onAddExpenseClick(view: View) {
        val description = descriptionEditText.text.toString()
        val amount = amountEditText.text.toString()
        val date = dateEditText.text.toString()
        val newExpense = Expense()
        newExpense.description = description
        newExpense.amount = amount
        newExpense.date = date
        //val expenseId = expenseDatabase.createExpense(description, amount, date)
        val expenseId = expenseDatabase.createExpense(newExpense)
        //Toast.makeText(this, "Created expense id ${expenseId}", Toast.LENGTH_SHORT).show()
        rebindExpensesListView()

        // Clear all input form fields
        descriptionEditText.setText("")
        amountEditText.setText("")
        dateEditText.setText("")
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

    fun onUpdateExpenseClick(view: View) {
        val selectedExpense = expenseDatabase.findOneExpense(editId)
        if (selectedExpense != null) {
            selectedExpense.description = descriptionEditText.text.toString()
            selectedExpense.amount = amountEditText.text.toString()
            selectedExpense.date = dateEditText.text.toString()
            val rowsUpdated = expenseDatabase.updateExpense(selectedExpense)
            Toast.makeText(this, "Updated successful", Toast.LENGTH_LONG)
                .apply { setGravity(Gravity.TOP, 0, 0) }
                .show()
        }
        rebindExpensesListView()
        onCancelClick(view)
    }

    fun onCancelClick(view: View) {
        editMode = false
        updateButton.visibility = View.GONE
        deleteButton.visibility = View.GONE
        cancelButton.visibility = View.GONE
        addButton.visibility = View.VISIBLE

        descriptionEditText.setText("")
        amountEditText.setText("")
        dateEditText.setText("")
    }

    fun onDeleteExpenseClick(view: View) {
        val rowsDeleted = expenseDatabase.deleteExpense(editId)
        if (rowsDeleted == 1) {
            Toast.makeText(this,"Deleted record ${editId}", Toast.LENGTH_LONG).show()
            rebindExpensesListView()
            onCancelClick(view)
        } else {
            Toast.makeText(this,"Delete was not successful", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_expenselist -> {
                val expenseListIntent = Intent(this, ExpenseListActivity::class.java)
                startActivity(expenseListIntent)
                return true
            }
            else ->  {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}