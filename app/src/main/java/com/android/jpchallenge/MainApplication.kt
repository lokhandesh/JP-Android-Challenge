package com.android.jpchallenge

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

    companion object {
        private lateinit var myApplication: MainApplication
        fun  getInstance(): MainApplication {
            if (!this::myApplication.isInitialized || myApplication == null) {
                myApplication = MainApplication()
            }
            return myApplication
        }
    }

}