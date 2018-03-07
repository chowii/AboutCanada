package au.com.cognizant.cognizantdev

import android.app.Application
import au.com.cognizant.cognizantdev.network.Constants

class CognizantApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Constants.init(this)
    }
}