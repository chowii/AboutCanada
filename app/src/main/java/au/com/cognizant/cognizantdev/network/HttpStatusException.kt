package au.com.cognizant.cognizantdev.network

import java.io.IOException

class HttpStatusException(private var error: List<String>?, var errorType: HttpErrorType) : IOException() {

    fun getMessageString(): String {
        var message = ""
        error?.forEach { message = it.plus(", ").plus(it) }
        return message
    }

}
