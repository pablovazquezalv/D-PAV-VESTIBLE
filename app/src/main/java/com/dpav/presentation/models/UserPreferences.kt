package com.dpav.presentation.models

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class UserPreferences(context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserLoggedIn(user: User, token: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", true)
        editor.putString("user", Gson().toJson(user))
        editor.putString("token", token)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("is_logged_in", false)
    }

    fun getUser(): User? {
        val user = sharedPreferences.getString("user", null)
        return user?.let { Gson().fromJson(it, User::class.java) }
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", false)
        editor.putString("user", null)
        editor.putString("token", null)
        editor.apply()
    }
}