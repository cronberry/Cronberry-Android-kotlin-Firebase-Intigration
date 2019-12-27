package com.blue.firebaseappdemo.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.blue.firebaseappdemo.DTO.UserRegister
import com.blue.firebaseappdemo.DTO.WebAPIResponse
import com.blue.firebaseappdemo.Preference.BitPreference
import com.blue.firebaseappdemo.R
import com.blue.firebaseappdemo.Utility.Utility
import kotlinx.android.synthetic.main.activity_main.*
import org.apache.commons.lang.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : BaseActivity(), View.OnClickListener {

    var bitPreference: BitPreference? = null

    override fun onClick(v: View?) {
        when (v) {
            button -> {
                if (isvalidate()) {

                    paramListValue(
                        name.text.toString(),
                        email.text.toString(),
                        mobile.text.toString(),
                        bitPreference!!.FIREBASE_TOKEN
                    )

                }
            }
        }
    }

    private fun isvalidate(): Boolean {
        var result = true

        if (!validObject(name)) {
            Toast.makeText(applicationContext, "Enter your name", Toast.LENGTH_SHORT).show()
            result = false
        } else if (!validObject(email)) {
            Toast.makeText(applicationContext, "Enter your email", Toast.LENGTH_SHORT).show()
            result = false
        } else if (!validObject(mobile)) {
            Toast.makeText(applicationContext, "Enter your mobile no.", Toast.LENGTH_SHORT).show()
            result = false
        }

        return result
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bitPreference = BitPreference(this)
        val toolbar = supportActionBar
        toolbar!!.title = getString(R.string.app_demo)
        button.setOnClickListener(this)

    }

    private fun paramListValue(name: String, email: String, mobile: String, firebasE_TOKEN: String) {

        val paramList = ArrayList<UserRegister.ParamList>()

        var list = UserRegister.ParamList()
        list.paramKey = "name"
        list.paramValue = name

        paramList.add(list)

        list = UserRegister.ParamList()
        list.paramKey = "email"
        list.paramValue = email

        paramList.add(list)

        list = UserRegister.ParamList()
        list.paramKey = "phone_no"
        list.paramValue = mobile

        paramList.add(list)

        list = UserRegister.ParamList()
        list.paramKey = "android_fcm_token"
        list.paramValue = firebasE_TOKEN

        paramList.add(list)


        userRegisterDTO(paramList)

    }

    fun userRegisterDTO(paramList: ArrayList<UserRegister.ParamList>) {

        val randomGenerator = Random()
        val randomInt = randomGenerator.nextInt(100)
        bitPreference!!.USERID = "" + randomInt


        val userRegister = UserRegister()
        userRegister.apiKey = "bXVrZXNoQG1hYXJ1amkuY29t"
        userRegister.audienceId = randomInt
        userRegister.paramList = paramList



        sendToServer(userRegister)
    }

    private fun sendToServer(userRegister: UserRegister) {
        showProgressBar()
        val retrofitObj = Utility.getRetrofitObj()
        val createProfile = retrofitObj?.registerAudience(userRegister)
        createProfile?.enqueue(object : Callback<WebAPIResponse> {

            override fun onFailure(call: Call<WebAPIResponse>, t: Throwable) {
                hideProgressBar()
                Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<WebAPIResponse>, response: Response<WebAPIResponse>) {
                hideProgressBar()
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Record inserted successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity, InAppNotification::class.java))
                } else {
                    Toast.makeText(applicationContext, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    private fun showProgressBar() {
        if (progressBar.visibility == View.GONE) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar() {
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
        }
    }


    private fun validObject(editText: EditText?): Boolean {
        val result: Boolean = null != editText && StringUtils.isNotBlank(editText.text.toString())
        return result
    }
}
