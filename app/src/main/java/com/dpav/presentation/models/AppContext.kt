package com.dpav.presentation.models

import android.annotation.SuppressLint
import android.content.Context

object ContextProvider {
    lateinit var context: Context
        private set

    fun init(context: Context) {
        this.context = context.applicationContext
    }
}
