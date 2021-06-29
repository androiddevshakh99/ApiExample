package uz.mobiller.apiexamp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.mobiller.apiexamp.model.JsonObjectItem

interface ApiInterface {
    @GET("json")
    fun getCurrentData():Call<List<JsonObjectItem>>
}