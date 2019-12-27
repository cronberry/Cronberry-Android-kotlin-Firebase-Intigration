package com.blue.firebaseappdemo.Activity

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import com.blue.firebaseappdemo.Preference.BitPreference
import com.blue.firebaseappdemo.R
import kotlinx.android.synthetic.main.activity_in_app_notification.*

class InAppNotification : BaseActivity() {
    var url: String? = null

    /* Your User Id is in String or Int */
    var audienceId: Any? = null

    /* Unique Generated API Key form Cronberry*/
    var apiKey: String? = null
    var bitPreference: BitPreference? = null

    var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_app_notification)
        bitPreference = BitPreference(this)
        val toolbar = supportActionBar
        toolbar!!.title = getString(R.string.inApp_toolbar)



        audienceId=bitPreference!!.USERID
        apiKey="bXVrZXNoQG1hYXJ1amkuY29t"

        url = "https://inapp.cronberry.com/mobile-view?audienceId=" + audienceId + "&" + "api-key="+apiKey
        inApp.loadUrl(url)
        inApp.settings.javaScriptEnabled = true
        inApp.settings.cacheMode = WebSettings.LOAD_NO_CACHE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.notification, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.inAppNotification -> {
                if (inApp.visibility == View.GONE) {
                    inApp.visibility = View.VISIBLE
                    textView2.visibility = View.GONE
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        if (inApp.visibility == View.VISIBLE) {
            inApp.visibility = View.GONE
            textView2.visibility = View.VISIBLE
        }
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
