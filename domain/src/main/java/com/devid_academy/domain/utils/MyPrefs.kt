package com.devid_academy.domain.utils

import android.content.SharedPreferences

class MyPrefs (private val sharedPreferences: SharedPreferences) {

    private val USER_ID = "user_id"
    private val USER_ROLE = "user_role"
    private val USER_NAME = "user_name"

    var user_id : Long
        set(value) = sharedPreferences.edit().putLong(USER_ID, value).apply()
        get() = sharedPreferences.getLong(USER_ID, 0)

    var user_role : Int
        set(value) = sharedPreferences.edit().putInt(USER_ROLE, value).apply()
        get() = sharedPreferences.getInt(USER_ROLE, 0)

    var user_name : String ?
        set(value) = sharedPreferences.edit().putString(USER_NAME, value).apply()
        get() = sharedPreferences.getString(USER_NAME,"")
}