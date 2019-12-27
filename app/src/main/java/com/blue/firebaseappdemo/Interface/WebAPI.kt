package com.blue.firebaseappdemo.Interface

import com.blue.firebaseappdemo.DTO.UserRegister
import com.blue.firebaseappdemo.DTO.WebAPIResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WebAPI {

    @POST("campaign/register-audience")
    fun registerAudience(@Body registerUser: UserRegister): Call<WebAPIResponse>
}