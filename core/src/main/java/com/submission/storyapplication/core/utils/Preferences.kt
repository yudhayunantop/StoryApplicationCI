package com.submission.storyapplication.core.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    private const val PREFS_NAME = "DataLogin"
    private const val MODE = Context.MODE_PRIVATE
    private const val KEY_userId = "KEY_userId"
    private const val KEY_name = "KEY_name"
    private const val KEY_token = "key_token"
    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFS_NAME, MODE)
    }

    fun clearData(){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getToken(): String? = preferences.getString(KEY_token, null)

    fun saveUserId(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_userId, userId)
        editor.apply()
    }

    fun saveName(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_name, userId)
        editor.apply()
    }

    fun saveToken(userId: String){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(KEY_token, userId)
        editor.apply()
    }
}