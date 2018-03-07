package au.com.cognizant.cognizantdev.network

import android.app.Application

class CognizantApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
    }
}