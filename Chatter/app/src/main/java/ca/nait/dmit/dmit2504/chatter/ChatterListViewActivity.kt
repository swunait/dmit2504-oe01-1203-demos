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
                //TODO("Not yet implemented")
                if (response.isSuccessful) {
                    val jittersString : String? = response.body()
                    if (jittersString != null) {
                        val delimiter = "\r\n"
                        val stringArray = jittersString.split(delimiter)

                        val loginNameList = mutableListOf<String>()
                        val messageList = mutableListOf<String>()
                        val dateList = mutableListOf<String>()
                        // for (int index =0 ; index < stringArray.size(); index += 3 )
                        for (index in 0 until stringArray.size step 3) {
                            val loginName: String = stringArray[index]
                            val message: String = stringArray[index+1]
                            val date: String = stringArray[index+2]

                            loginNameList.add(loginName)
                            messageList.add(message)
                            dateList.add(date)
                        }

                        //Log.i(TAG, jittersString)

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