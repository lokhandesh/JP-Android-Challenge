package com.android.jpchallenge.core.api

import com.android.jpchallenge.feature.domain.model.Animal
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {



    @GET("animals/rand/10")
    fun fetchAnimalList(): Single<Animal>

    /*@POST("transfer")
    fun transfer(@Body pay: TransferReqParam): Single<ApiResponse>*/

}