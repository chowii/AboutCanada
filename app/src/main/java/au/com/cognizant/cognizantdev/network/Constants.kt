package au.com.cognizant.cognizantdev.network

import android.content.Context
import au.com.cognizant.cognizantdev.R

class Constants {

    companion object {

        var ENDPOINT = ""

        fun init(context: Context) {
            ENDPOINT = context.getString(R.string.endpoint)
        }
    }

}