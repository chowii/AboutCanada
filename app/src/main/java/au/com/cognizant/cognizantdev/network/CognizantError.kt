package au.com.cognizant.cognizantdev.network

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response
import java.io.IOException

class CognizantError {

    @SerializedName("error")
    var error: List<String>? = null

    val message: String
        get() {
            if (error == null) {
                return "Failed to complete request, please try again."
            }
            val message = StringBuilder()
            for (s in error!!)
                message.append(", ").append(s)

            return message.toString()
        }

    companion object {

        fun fromResponse(response: Response?): CognizantError {
            var error: CognizantError? = CognizantError()
            try {
                val errorBodyString = response?.body()!!.string()
                error = fromJson(errorBodyString)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (error == null) {
                error = CognizantError()
            }
            return error
        }

        fun fromJson(json: String): CognizantError {
            try {
                return Gson().fromJson(json, CognizantError::class.java)
            } catch (e: Exception) {
                return CognizantError()
            }

        }
    }

}
