package xyz.arifz.vumobile.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserEndPoint {

    @GET("api/users")
    fun getUserListFromServer(@Query("page") pageNumber: Long): Call<UserListResponse>
    
}