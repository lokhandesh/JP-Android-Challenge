package com.android.jpchallenge.feature.domain.repository

import com.android.jpchallenge.feature.domain.model.Animal
import io.reactivex.Single


interface AnimalRepository {

    fun fetchAnimalList(): Single<Animal>

}