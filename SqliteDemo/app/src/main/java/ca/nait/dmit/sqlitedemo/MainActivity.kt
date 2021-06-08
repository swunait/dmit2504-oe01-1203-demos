package ca.nait.dmit.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val expenseDatabase = ExpenseDatabase(this)

    private val descriptionEditText : EditText by lazy { findViewById(R.id.activity_main_description_edittext) }
    private val amountEditText : EditText by lazy { findViewById(R.id.activity_main_amount_edittext) }
    private val dateEditText : EditText by lazy { findViewById(R.id.activity_main_date_edittext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"${expenseDatabase.expenses.count} records in db", Toast.LENGTH_LONG).show()
    }

    fun onAddExpenseClick(view: View) {
        val description = descriptionEditText.text.toString()
        val amount = amountEditText.text.toString()
        val date = dateEditText.text.toString()
        val expenseId = expenseDatabase.createExpense(description, amount, date)
        Toast.makeText(this, "Created expense id ${expenseId}", Toast.LENGTH_SHORT).show()
    }
}