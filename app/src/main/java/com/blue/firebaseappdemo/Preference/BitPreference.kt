package com.blue.firebaseappdemo.Preference

import android.content.Context
import android.content.SharedPreferences


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class BitPreference (context: Context) {

    fun clearAll() {
        prefs.edit().clear().apply()
    }
    private val PREFS_FILENAME = "co.asachs.bitbuddy"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    private val USERID_PREF = "userId_pref"
    private val EMAIL_PREF = "email_pref"
    private val MOBILE_PREF = "mobile_pref"
    private val NAME_PREF = "name_pref"
    private val FIREBASE_PREF = "firebase_pref"

    var USERID: String
        get() = prefs.getString(USERID_PREF, "")!!
        set(value) = prefs.edit().putString(USERID_PREF, value).apply()

    var NAME: String
        get() = prefs.getString(NAME_PREF, "")!!
        set(value) = prefs.edit().putString(NAME_PREF, value.toUpperCase()).apply()

    var FIREBASE_TOKEN: String
        get() = prefs.getString(FIREBASE_PREF, "")!!
        set(value) = prefs.edit().putString(FIREBASE_PREF, value).apply()


    var MOBILE: String
        get() = prefs.getString(MOBILE_PREF, "")!!
        set(value) = prefs.edit().putString(MOBILE_PREF, value.toUpperCase()).apply()

    var EMAIL: String
        get() = prefs.getString(EMAIL_PREF, "")!!
        set(value) = prefs.edit().putString(EMAIL_PREF, value.toUpperCase()).apply()

}