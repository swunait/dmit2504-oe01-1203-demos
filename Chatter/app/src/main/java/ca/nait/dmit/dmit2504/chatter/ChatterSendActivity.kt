package ca.nait.dmit.dmit2504.chatter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class ChatterSendActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatter_send)

        val sendButton: Button = findViewById(R.id.button_send_data)
        sendButton.setOnClickListener(this)

        val viewJittersButton = findViewById<Button>(R.id.button_view_jitters)
        viewJittersButton.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.menu_item_view_chats -> {
                val intent = Intent(this, ChatterReceiveActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item_view_list -> {
//                val intent = Intent(this, ChatterSendActivity::class.java)
//                startActivity(intent)
            }
            R.id.menu_item_view_jitter_list -> {
                val listViewIntent = Intent(this, ChatterListViewActivity::class.java)
                startActivity(listViewIntent)
            }
        }
        return true
    }

    override fun onClick(v: View?) {
//        TODO("Not yet implemented")
        when (v?.id) {
            R.id.button_send_data -> {
                val dataEditText : EditText = findViewById(R.id.textbox_data)
                val message = dataEditText.text.toString()
                postToChatter(message)
                dataEditText.setText("")
//                Toast.makeText(this, "POST successful", Toast.LENGTH_SHORT).show()
            }
            R.id.button_view_jitters -> {
                val intent = Intent(this, ChatterReceiveActivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun postToChatter(message: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.youcode.ca/")
//            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val youcodeService = retrofit.create(YoucodeService::class.java)

        val postCall = youcodeService.postJitter(message,"dmit2504swu")

        postCall.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Toast.makeText(this@ChatterSendActivity,"POST successful", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@ChatterSendActivity, "POST failure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}