package ca.nait.dmit.dmit2054.exercisehockeyteamdivision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val divisionNameEditText: EditText by lazy { findViewById(R.id.activity_main_name_edittext) }
    private val divisionSpinner: Spinner by lazy { findViewById(R.id.activity_main_division_spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        divisionSpinner.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this,"Your selected",Toast.LENGTH_SHORT).show()
        }

        rebindDivisionSpinner()
    }

    fun onAddDivisionClick(view: View) {
        val divisionName = divisionNameEditText.text.toString()
        if (divisionName.isNotBlank()) {
            val db = HockeyDatabase(this)
            val rowId = db.insertDivision(divisionName)
            if (rowId > 0) {
                divisionNameEditText.setText("")
                Toast.makeText(this,"Division Name added to database", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Error adding name to database", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this,"Division Name is required", Toast.LENGTH_SHORT).show()
        }
    }

    fun rebindDivisionSpinner() {
        val db = HockeyDatabase(this)
        val divisionCursor = db.listDivisions()
        val columnNames = arrayOf(HockeyDatabase.DivisionContract.DivisionEntry.COLUMN_NAME_NAME)
        val viewIds = intArrayOf(android.R.id.text1)
        val divisionAdapter = SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,divisionCursor, columnNames, viewIds, 0)
        divisionSpinner.adapter = divisionAdapter
    }
}