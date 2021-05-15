package ca.nait.dmit.dmit2504.chatter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ChatterReceiveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatter_receive)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone1.app.dmitcapstone.ca/api/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        val httpClient = retrofit.create(ChatterService::class.java)

        val callGetChatters = httpClient.getChatters();

        callGetChatters.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
//                TODO("Not yet implemented")
                if (response.isSuccessful) {
                    val textbox = findViewById<TextView>(R.id.textbox_receive_chatter)
                    textbox.text = response.body()
                } else {
                    Log.e("ChatterReceiveActivity","Failed to get chatters.")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
//                TODO("Not yet implemented")
                Log.e("ChatterReceiveActivity","Failed to communicate with web api.")
            }

        })


    }
}