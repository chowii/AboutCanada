package au.com.cognizant.cognizantdev.network


import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class StatusCodeInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response = chain.proceed(request)

        val statusCode = response.code()
        if (statusCode < 200 || statusCode > 299 || response.body() == null) {
            Log.d("TAG", "interceptStatusCode: " + statusCode)
            handleErrorResponse(response)
        }

        return response
    }

    @Throws(HttpStatusException::class)
    private fun handleErrorResponse(response: Response?) {
        if (response == null) {
            val error = CognizantError.fromResponse(response)
            throw HttpStatusException(error.error, HttpErrorType.General)
        }
        val error = CognizantError.fromResponse(response)
        throw HttpStatusException(error.error, HttpErrorType.General)
    }
}