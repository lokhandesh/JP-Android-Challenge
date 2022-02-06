package com.android.jpchallenge.feature.domain.usecase

import com.android.jpchallenge.feature.domain.model.Animal
import com.android.jpchallenge.feature.domain.repository.AnimalRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnimalListUseCaseTest {

    lateinit var animalListUseCase: AnimalListUseCase

    @Mock
    lateinit var animalRepository: AnimalRepository

    private var animal = Animal()

    private lateinit var animalItem: Animal.AnimalItem

    @Before
    @Throws(Exception::class)
    fun setUp() {
        animalRepository = Mockito.mock(AnimalRepository::class.java)
        //  authDataSource = Mockito.mock(AuthDataSource::class.java)
        animalListUseCase = AnimalListUseCase(animalRepository)

        animalItem = Animal.AnimalItem("Diurnal","Reptile","Small animals, such as insects and crabs, and eggs",
            "New Guinea and northern Australia","Forest and swamp",81,"https://upload.wikimedia.org/wikipedia/commons/d/d6/Varanus_prasinus.jpg",
            "Varanus prasinus","3.3","2.5","20","Green Tree Monitor","0.7","0.6")

        animal.add(animalItem)

    }

    @Test
    fun testbuildUseCaseSingleSuccessList() {
        Mockito.doReturn(Single.just(animal)).`when`(animalRepository).fetchAnimalList()
        var result = animalListUseCase.buildUseCaseSingle()
        assert(!result.blockingGet().isNullOrEmpty())
    }

    @Test
    fun testbuildUseCaseSingleListFail() {
        Mockito.doReturn(Single.just(Animal())).`when`(animalRepository).fetchAnimalList()
        var result = animalListUseCase.buildUseCaseSingle()
        assert(!result.blockingGet().isNullOrEmpty())
    }



}