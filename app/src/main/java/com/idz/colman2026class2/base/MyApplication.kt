package com.idz.colman2026class2.base

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    companion object Globals {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}