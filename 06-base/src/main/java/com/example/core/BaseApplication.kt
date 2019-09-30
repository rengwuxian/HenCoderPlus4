package com.example.core

import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        currentApplication = this
    }

    companion object {

        @get:JvmName("currentApplication")
        @get:JvmStatic
        lateinit var currentApplication: Context
            private set
    }
}