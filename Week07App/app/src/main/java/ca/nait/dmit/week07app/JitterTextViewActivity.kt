package ca.nait.dmit.week07app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

const val GET_URL_STRING = "https://capstone1.app.dmitcapstone.ca/api/jitters/Week05Servlet"

class JitterTextViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jitter_text_view)

        // Using the NetworkAPI helper class call the fetchUrlResponseString with GET_URL_STRING
        // and set the text of the data textview with the response data

        // Create an instance of the NetworkAPI
        val networkAPI = NetworkAPI()

        // Find the text view to set the text
        val dataTextView: TextView = findViewById(R.id.activity_jitter_text_view_data)

        // Create a custom CoroutineScope with the IO dispatcher
        CoroutineScope(Dispatchers.IO).launch {
            // Call the fetchUrlResponseString method
            val responseData = networkAPI.fetchUrlResponseString(GET_URL_STRING)
            withContext(Dispatchers.Main) {
                // Set the text of dataTextView to responseData
                dataTextView.setText(responseData)
            }
        }
    }
}