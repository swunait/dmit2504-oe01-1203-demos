package ca.nait.dmit.dmit2504

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"
    var textviewState : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i(TAG, "onCreate event")

        //textviewState = findViewById<TextView>(R.id.textview_state)
        textviewState = findViewById(R.id.textview_state)
        textviewState?.append("onCreate event\n")
    }

    override fun onStart() {
        super.onStart()

        Log.i(TAG, "onStart event")
        textviewState?.append("onStart event\n")
    }

    override fun onResume() {
        super.onResume()

        Log.i(TAG, "onResume event")
        textviewState?.append("onResume event\n")
    }

    override fun onPause() {
        super.onPause()

        Log.i(TAG, "onPause event")
        textviewState?.append("onPause event\n")
    }

    override fun onStop() {
        super.onStop()

        Log.i(TAG,"onStop event")
        textviewState?.append("onStop event\n")
    }

    override fun onRestart() {
        super.onRestart()

        Log.i(TAG, "onRestart event")
        textviewState?.append("onRestart event")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i(TAG, "onDestroy event")
    }

}