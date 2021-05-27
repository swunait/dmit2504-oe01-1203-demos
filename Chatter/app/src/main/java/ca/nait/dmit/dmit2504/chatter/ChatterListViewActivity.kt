package ca.nait.dmit.dmit2504.chatter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.BufferedReader
import java.io.StringReader
import java.lang.Exception

class ChatterListViewActivity : AppCompatActivity() {

    val TAG : String = "ChatterListViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatter_list_view)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.youcode.ca/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

        val youcodeService = retrofit.create(YoucodeService::class.java)

        val listJitterServletCall = youcodeService.listJitterServlet()

        listJitterServletCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val jittersString : String? = response.body()
                    if (jittersString != null) {
                        val reader = BufferedReader(StringReader(jittersString))
                        var line = reader.readLine()
                        while (line != null) {
                            try {
                                val loginName = line
                                val data = reader.readLine()
                                val date = reader.readLine()
                                val currentJitter = Jitter(loginName,data,date)
                                // call the addItem method of the data adapter


                                line = reader.readLine()
                            } catch(e: Exception) {

                            }
                        }

                    }
                    //Log.i(TAG, jittersString!!)


                } else {
                    Log.e(TAG, "Error retrieving Jitters")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //TODO("Not yet implemented")
                Log.e(TAG, "Failed to communicated with web api")
            }

        })
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
                val intent = Intent(this, ChatterSendActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item_view_jitter_list -> {
//                val listViewIntent = Intent(this, ChatterListViewActivity::class.java)
//                startActivity(listViewIntent)
            }
        }
        return true
    }
}