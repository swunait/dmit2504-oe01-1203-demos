package ca.nait.dmit.week07app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val POST_URL_STRING = "https://capstone1.app.dmitcapstone.ca/api/jitters/JitterServlet"

class MainActivity : AppCompatActivity() {
    private val messageEditText : EditText
        by lazy { findViewById(R.id.activity_main_message_edittext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onPostMessageButtonClick(view: View) {

        //Thread.sleep(10000)
        // Create a new NetworkAPI instance
        val networkAPI = NetworkAPI()
        // Read the text from the message EditText
        val message = messageEditText.text.toString()
        // Create a HashMap instance and set the data to send to the server
        val formDataMap = HashMap<String,String>()
        formDataMap.put("DATA", message)
        formDataMap.put("LOGIN_NAME","dmit2504swu")

        CoroutineScope(Dispatchers.IO).launch {
            // Call the postFormData method of the NetworkAPI instance
            networkAPI.postFormData(POST_URL_STRING, formDataMap)
            withContext(Dispatchers.Main) {
                // Show a Toast message when its completed
                Toast.makeText(this@MainActivity, "POST successful.", Toast.LENGTH_LONG).show()
                // Clear the text from the message EditText
                messageEditText.setText("")
            }
        }

    }
}