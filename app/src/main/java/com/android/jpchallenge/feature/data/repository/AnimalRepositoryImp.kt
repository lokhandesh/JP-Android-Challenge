package com.android.jpchallenge.feature.data.repository

import com.android.jpchallenge.feature.data.source.remote.AnimalDataSource
import com.android.jpchallenge.feature.domain.model.Animal
import com.android.jpchallenge.feature.domain.repository.AnimalRepository
import io.reactivex.Single
import javax.inject.Inject


/**
 * This repository is responsible for
 * fetching data[Animal] from server or db
 * */
class AnimalRepositoryImp @Inject constructor(
    private val animalDataSource: AnimalDataSource
) :
    AnimalRepository {
    override fun fetchAnimalList(): Single<Animal> {
        return animalDataSource.fetchAnimalList()
    }

}