package ca.nait.dmit.lesson1_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickHandler(view: View) {
        val textData = findViewById<EditText>(R.id.edittext_data)
        val data = textData.text.toString()

        val intent = Intent(this, ReceiveActivity::class.java)

        val bundle = Bundle()
        bundle.putString("PREFIX","You typed:")
        bundle.putString("DATA", data)
        intent.putExtras(bundle)

        startActivity(intent)

    }
}