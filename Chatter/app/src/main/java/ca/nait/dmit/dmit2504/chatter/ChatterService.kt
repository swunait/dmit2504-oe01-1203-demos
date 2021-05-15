package ca.nait.dmit.dmit2504.chatter

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Chatter(val loginName: String, val data: String)

interface ChatterService {

    @GET("jitters/JSONServlet")
    fun getChatters() : Call<String>

//    @GET("jitters")
//    fun listChatters() : Call<List<Chatter>>

    @POST("jitters")
    fun postChatter(@Body newChatter: Chatter) : Call<ResponseBody>


}