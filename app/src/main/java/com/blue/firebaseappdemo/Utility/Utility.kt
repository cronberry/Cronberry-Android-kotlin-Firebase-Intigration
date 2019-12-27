package com.blue.firebaseappdemo.Utility

import com.blue.firebaseappdemo.Interface.WebAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Utility {
    companion object {
        var isAppInBackground: Boolean? = false







        fun getRetrofitObj(): WebAPI? {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseUrl)
                .build()

            return retrofit.create(WebAPI::class.java)
        }
    }


}
