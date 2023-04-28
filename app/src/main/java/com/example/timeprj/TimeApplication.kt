package com.example.timeprj

import android.app.Application

class TimeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TimeApplication
            private set
    }
}