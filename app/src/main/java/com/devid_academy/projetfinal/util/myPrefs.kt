package com.devid_academy.projetfinal.util

import android.content.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

class MyPrefs (private val sharedPreferences: SharedPreferences) {

    private val USER_ID = "user_id"
    private val USER_ROLE = "user_role"
    private val TOKEN = "token"

    var user_id : Long
        set(value) = sharedPreferences.edit().putLong(USER_ID, value).apply()
        get() = sharedPreferences.getLong(USER_ID, 0)

    var user_role : Int
        set(value) = sharedPreferences.edit().putInt(USER_ROLE, value).apply()
        get() = sharedPreferences.getInt(USER_ID, 0)

}