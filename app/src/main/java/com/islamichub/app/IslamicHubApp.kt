package com.islamichub.app

import android.app.Application

class IslamicHubApp : Application() {
    companion object {
        lateinit var instance: IslamicHubApp
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
