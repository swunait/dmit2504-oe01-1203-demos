package ca.nait.dmit.dmit2504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class GameActivity : AppCompatActivity() {
    val difficultyTextView: TextView by lazy { findViewById(R.id.activity_game_difficulty_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        //val selectedDifficulty = intent.getStringExtra("GAME_DIFFICULTY")
        val selectedDifficulty = intent.getStringExtra(MainActivity.GAME_DIFFICULTY)
        difficultyTextView.text = selectedDifficulty
    }
}