package com.android.jpchallenge

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MainApplication : Application() {
    /*companion object{
         var user: User? = null
    }*/

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

    }

}