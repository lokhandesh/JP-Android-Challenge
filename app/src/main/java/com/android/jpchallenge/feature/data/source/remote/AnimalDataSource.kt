package com.android.jpchallenge.feature.data.source.remote

import com.android.jpchallenge.core.api.RetrofitService
import com.android.jpchallenge.feature.domain.model.Animal
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[Animal] from server
 * */
abstract class AnimalDataSource {
    abstract fun fetchAnimalList(): Single<Animal>

}

class AnimalSourceImp @Inject constructor(
    private val retrofitService: RetrofitService
) : AnimalDataSource() {


    override fun fetchAnimalList(): Single<Animal> {
        return retrofitService.fetchAnimalList()
    }




}
