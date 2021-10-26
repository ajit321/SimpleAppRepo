package com.sample.dishes.app

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates


@HiltAndroidApp
class SimpleApp : Application(){

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        //FirebaseApp.initializeApp(this)
    }

}