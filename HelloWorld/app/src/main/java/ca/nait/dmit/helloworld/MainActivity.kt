package ca.nait.dmit.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button in the activity
        val submitButton = findViewById<Button>(R.id.button_submit)

//        submitButton.setOnClickListener {
//            val usernameEditText = findViewById<EditText>(R.id.edittext_username)
//            val greetingTextView = findViewById<TextView>(R.id.textview_greeting_message)
//            if (!TextUtils.isEmpty(usernameEditText.text)) {
//                val username = usernameEditText.text
//                greetingTextView.text = "Hello ${username}"
//            } else {
//                greetingTextView.text = "Hello World!"
//            }
//        }

//        submitButton.setOnClickListener(this::onSubmitClick)

    }

    fun onSubmitClick(view: View) {
        val usernameEditText = findViewById<EditText>(R.id.edittext_username)
        val greetingTextView = findViewById<TextView>(R.id.textview_greeting_message)
        if (!TextUtils.isEmpty(usernameEditText.text)) {
            val username = usernameEditText.text
            greetingTextView.text = "Hello ${username}"
            Snackbar.make(view,"Hello ${username}", Snackbar.LENGTH_LONG).show()
        } else {
            greetingTextView.text = "Hello World!"
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
        }
    }
}