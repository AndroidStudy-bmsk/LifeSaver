package com.lifesaver

import android.app.Application
import com.lifesaver.data.LifeSaverPreferences

class LifeSaverApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        LifeSaverPreferences.init(applicationContext)
    }
}