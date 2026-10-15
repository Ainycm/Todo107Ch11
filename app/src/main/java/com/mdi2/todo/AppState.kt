package com.mdi2.todo

import android.content.Context
import android.util.Log

object AppState {

    private var applicationContext: Context? = null

    fun initialize(context: Context) {

        if (applicationContext == null) {

            applicationContext = context.applicationContext

            Log.w(
                "AppState",
                "Application context stored"
            )
        }
    }
}