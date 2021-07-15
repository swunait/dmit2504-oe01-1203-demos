package ca.nait.dmit.sqlitedemo

import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cursoradapter.widget.CursorAdapter

class ExpenseCursorAdapter(context: Context, cursor: Cursor, flags: Int) : CursorAdapter(context,cursor,flags) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.list_item_cursor_expenses, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val idTextView: TextView = view?.findViewById(R.id.list_item_cursor_expenses_expenseid_textview)!!
        val descriptionTextView: TextView = view?.findViewById(R.id.list_item_cursor_expenses_description_textview)!!
        val amountTextView: TextView = view?.findViewById(R.id.list_item_cursor_expenses_amount_textview)!!
        val dateTextView: TextView = view?.findViewById(R.id.list_item_cursor_expenses_date_textview)!!
//        val updateButton: Button = view?.findViewById(R.id.list_item_cursor_expenses_update_button)!!
        val deleteButton: Button = view?.findViewById(R.id.list_item_cursor_expenses_delete_button)!!

        val expenseId: Int = cursor?.getInt(cursor?.getColumnIndexOrThrow(BaseColumns._ID)) ?: 0
        idTextView.setText("${expenseId}")

        val description: String = cursor?.getString(cursor?.getColumnIndexOrThrow(ExpenseDatabase.TABLE_EXPENSE_COLUMN_DESCRIPTION)) ?: "no desc"
        descriptionTextView.setText(description)


        val amount: String = cursor?.getString(cursor?.getColumnIndexOrThrow(ExpenseDatabase.TABLE_EXPENSE_COLUMN_AMOUNT)) ?: "0.00"
        amountTextView.setText(amount)

        val date: String = cursor?.getString(cursor?.getColumnIndexOrThrow(ExpenseDatabase.TABLE_EXPENSE_COLUMN_DATE)) ?: "no date"
        dateTextView.setText(date)

        deleteButton.setOnClickListener { view: View ->
            val db = ExpenseDatabase(context!!)
            db.deleteExpense(expenseId.toLong())
            Toast.makeText(context!!,"Deleted ${expenseId}", Toast.LENGTH_SHORT).show()
            changeCursor(db.expenses)
            notifyDataSetChanged()
        }

//        updateButton.setOnClickListener { view: View ->
//            val db = ExpenseDatabase(context!!)
//            var existingExpense = Expense()
//            existingExpense.id = expenseId.toLong()
//            existingExpense.description = description
//            existingExpense.amount = amount
//            existingExpense.date = date
//            db.updateExpense(existingExpense)
//            notifyDataSetChanged()
//            Toast.makeText(context!!,"Updated was successful", Toast.LENGTH_SHORT).show()
//        }

    }

}