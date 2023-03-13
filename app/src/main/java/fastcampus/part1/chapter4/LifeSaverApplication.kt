package fastcampus.part1.chapter4

import android.app.Application
import fastcampus.part1.chapter4.data.LifeSaverPreferences

class LifeSaverApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        LifeSaverPreferences.init(applicationContext)
    }
}