package ca.nait.dmit.dmit2504.chatter

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YoucodeService {

    @GET("JitterServlet")
    fun listJitterServlet(): Call<String>   // Response body format: [loginName]\r\n[data]\r\n[date]

    @GET("JSONServlet")
    fun listJSONServlet() : Call<String>    // Response body format: [date] from [sender] *** [data]

    @POST("JitterServlet")
    @FormUrlEncoded
    fun postJitter(@Field("DATA") data: String, @Field("LOGIN_NAME") loginName: String) : Call<ResponseBody>

}

class Jitter(
    var loginName: String = "",
    var data: String = "",
    var date: String? = null
)
