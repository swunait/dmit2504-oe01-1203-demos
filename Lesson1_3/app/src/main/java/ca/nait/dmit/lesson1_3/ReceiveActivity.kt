package ca.nait.dmit.lesson1_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive)

        if (intent != null && intent.extras != null) {
            val bundle = intent.extras
            val prefix = bundle?.getString("PREFIX")
            val data = bundle?.getString("DATA")

            val textbox = findViewById<TextView>(R.id.text_view_receive)
            textbox.text = prefix + data

        }
    }

    fun onCloseActivity(view: View?) {
        finish()
    }
}