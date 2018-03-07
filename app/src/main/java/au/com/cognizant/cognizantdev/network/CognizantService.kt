package au.com.cognizant.cognizantdev.network

import au.com.cognizant.cognizantdev.feature.model.AboutCanada
import io.reactivex.Observable
import retrofit2.http.GET

interface CognizantService {

    @GET("facts.json")
    fun getDataset(): Observable<AboutCanada>

}
