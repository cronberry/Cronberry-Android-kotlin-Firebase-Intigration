package com.blue.firebaseappdemo.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import com.blue.firebaseappdemo.Preference.BitPreference
import com.blue.firebaseappdemo.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import org.apache.commons.lang.StringUtils


class SplashActivity : BaseActivity() {
    var bitPreference: BitPreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        bitPreference = BitPreference(this)
        pushFirebase()
        Handler().postDelayed({
            if (StringUtils.isNotBlank(bitPreference!!.USERID)) {
                val i = Intent(this@SplashActivity, InAppNotification::class.java)
                startActivity(i)
                finish()
            } else {
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
                finish()
            }

        }, 3000)
    }

    private fun pushFirebase() {
        FirebaseMessaging.getInstance();
        val firebaseToken = FirebaseInstanceId.getInstance().getToken()
        bitPreference!!.FIREBASE_TOKEN = firebaseToken.toString()
        Log.d("chingfcm",firebaseToken)

    }
}
