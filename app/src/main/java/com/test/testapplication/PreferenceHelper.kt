package com.test.testapplication

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object PreferenceHelper {

    private const val LOGGED_USER = "USER"
    private const val NAME = "CravePref"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private var gson: Gson? = null

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
        gson = Gson()
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun setUserLogged(isUserLogged: Boolean) {
        preferences.edit {
            it.putBoolean(LOGGED_USER, isUserLogged)
        }
    }

    fun getUserLogged(): Boolean {
        return preferences.getBoolean(LOGGED_USER, false)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}