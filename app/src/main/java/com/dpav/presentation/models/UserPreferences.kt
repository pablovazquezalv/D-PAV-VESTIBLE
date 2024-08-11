package com.dpav.presentation.models

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UserPreferences(context: Context) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean = sharedPreferences.getBoolean("is_logged_in", false)
    var token: String? = sharedPreferences.getString("token", null)

    // Guarda la información del usuario y el token
    fun saveUserLoggedIn(user: User, token: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", true)
        editor.putString("user", Gson().toJson(user))
        editor.putString("token", token)
        editor.apply()

        KtorClient.setInstance()
        //Log.d("UserPreferences", "User saved: ${Gson().toJson(user)}, Token: $token")
    }

    // Verifica si el usuario está logueado

    // Obtiene el usuario guardado
    fun getUser(): User? {
        val user = sharedPreferences.getString("user", null)
        return user?.let { Gson().fromJson(it, User::class.java) }
    }

    // Obtiene el token guardado

    // Cierra la sesión y borra los datos guardados
    fun logout() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("is_logged_in", false)
        editor.putString("user", null)
        editor.putString("token", null)
        editor.apply()

        //Log.d("UserPreferences", "User logged out and data cleared.")
    }
}