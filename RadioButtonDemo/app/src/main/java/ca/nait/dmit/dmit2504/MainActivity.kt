package ca.nait.dmit.dmit2504

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // companion object replaces "static" members in Java/C#
    companion object {
        const val GAME_DIFFICULTY = "ca.nait.dmit.dmit2504.GAME_DIFFCULTY"
    }

    val difficultyRadioGroup : RadioGroup by lazy { findViewById(R.id.activity_main_difficulty_radiogroup)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSubmit(view: View) {
        val selectedDifficulty = getGameDifficulty(difficultyRadioGroup.checkedRadioButtonId)

        // Create an new intent to start the GameActivity
        val startGameActivityIntent = Intent(this, GameActivity::class.java)
//        startGameActivityIntent.putExtra("GAME_DIFFICULTY",selectedDifficulty)
        startGameActivityIntent.putExtra(MainActivity.GAME_DIFFICULTY, selectedDifficulty)
        startActivity(startGameActivityIntent)

        //Toast.makeText(this, "You selected ${selectedDifficulty}", Toast.LENGTH_LONG).show()

    }

    fun getGameDifficulty(checkButtonId: Int) : String {
        var difficulty : String
        when (checkButtonId) {
            R.id.activity_main_difficulty_easy_radiobutton -> difficulty = "EASY"
            R.id.activity_main_difficulty_normal_radiobutton -> difficulty = "NORMAL"
            R.id.activity_main_difficulty_hard_radiobutton -> difficulty = "HARD"
            else -> difficulty= ""
        }
        return difficulty
    }
}