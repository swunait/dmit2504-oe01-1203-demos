package ca.nait.dmit.sqlitedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class ExpenseListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        val expenseListView: ListView = findViewById(R.id.activity_expense_list_listview)
        val db = ExpenseDatabase(this)
        val expensesCursor = db.expenses
        val expenseAdapter = ExpenseCursorAdapter(this, expensesCursor, 0)
        expenseListView.adapter = expenseAdapter

    }
}