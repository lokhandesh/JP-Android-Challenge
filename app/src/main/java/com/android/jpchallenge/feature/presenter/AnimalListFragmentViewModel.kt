package com.android.jpchallenge.feature.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.jpchallenge.feature.domain.model.Animal
import com.android.jpchallenge.feature.domain.usecase.AnimalListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimalListFragmentViewModel @Inject constructor(private val animalListUseCase: AnimalListUseCase) : ViewModel() {

    val animalData = MutableLiveData<Animal>()
    val errorResponse = MutableLiveData<Boolean>()

    init {
        fetchAnimalList()
    }

    fun fetchAnimalList() {
        animalListUseCase.execute(
            onSuccess = {
               // MainApplication.user = it
                animalData.value = it
               // errorResponse.value = false
            },
            onError = {
                errorResponse.value = true
            }
        )
    }


}