package uz.fayyoz.a1shop

import android.app.Application

class App : Application() {

    companion object {
        lateinit var appInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
}