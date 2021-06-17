package ca.nait.dmit.exerciseguessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // Define constants of initial lower limit and upper limit and TAG
    companion object {
        const val TAG = "MainActivity"
        const val INITIAL_LOWER_LIMIT = 1
        const val INITIAL_UPPER_LIMIT = 100
    }
    // Create variables to track guessCount, guessLowerLimit, guessUpperLimit, randomNumber
    private var guessCount = 0
    private var guessLowerLimit = INITIAL_LOWER_LIMIT
    private var guessUpperLimit = INITIAL_UPPER_LIMIT
    private var randomNumber = 0

    // Declare variables to track the views in the activity
    private val guessRangeTextView: TextView by lazy { findViewById(R.id.activity_main_range_textview) }
    private val guessEditText : EditText by lazy { findViewById(R.id.activity_main_guess_edittext) }
    private val guessResultTextView: TextView by lazy { findViewById(R.id.activity_main_guessresult_textview) }
    private val guessCountTextView : TextView by lazy { findViewById(R.id.activity_main_guesscount_textview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetGame()
    }

    fun resetGame() {
        // Generate a random number between INITIAL lower limit and upper limit
        randomNumber = Random.nextInt(INITIAL_LOWER_LIMIT, INITIAL_UPPER_LIMIT)
        guessLowerLimit = INITIAL_LOWER_LIMIT
        guessUpperLimit = INITIAL_UPPER_LIMIT
        guessCount = 0
        val guessNumber : Int = (guessUpperLimit - guessLowerLimit) / 2 + 1
        guessEditText.setText("$guessNumber")

        guessRangeTextView.setText(getString(R.string.min_and_max, guessLowerLimit, guessUpperLimit))
        guessResultTextView.setText("")
        guessCountTextView.setText(getString(R.string.number_of_guesses, guessCount))
    }

    fun onGuessButtonClick(view: View) {

    }
}