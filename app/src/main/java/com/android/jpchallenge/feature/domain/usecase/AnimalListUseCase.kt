package com.android.jpchallenge.feature.domain.usecase

import com.android.jpchallenge.core.useCase.SingleUseCase
import com.android.jpchallenge.feature.domain.model.Animal
import com.android.jpchallenge.feature.domain.repository.AnimalRepository
import io.reactivex.Single
import javax.inject.Inject


class AnimalListUseCase @Inject constructor(private val repository: AnimalRepository) :
    SingleUseCase<Animal>() {
    override fun buildUseCaseSingle(): Single<Animal> {
        return repository.fetchAnimalList()
    }

    fun buildTest(): Boolean {
        return (repository.fetchAnimalList()
            .blockingGet().isEmpty()
                )
    }
}