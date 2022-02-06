package com.android.jpchallenge.feature.presenter

import com.android.jpchallenge.core.api.RetrofitService
import com.android.jpchallenge.feature.domain.model.Animal
import com.android.jpchallenge.feature.domain.usecase.AnimalListUseCase
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AnimalListFragmentViewModelTest {


    @InjectMocks
    lateinit var animalListFragmentViewModel: AnimalListFragmentViewModel

    @Mock
    lateinit var retrofitService: RetrofitService

    @Mock
    lateinit var animalListUseCase: AnimalListUseCase

    private var singleResponse: Single<Animal>? = null

    private var animal = Animal()

    private lateinit var animalItem: Animal.AnimalItem

    @Before
    @Throws(Exception::class)
    fun setUp() {

        retrofitService = Mockito.mock(RetrofitService::class.java)
        animalListUseCase = Mockito.mock(AnimalListUseCase::class.java)
        animalListFragmentViewModel = AnimalListFragmentViewModel(animalListUseCase)
    }

    @Test
    fun testFetchAnimalList() {


        animalItem = Animal.AnimalItem(
            "Diurnal",
            "Reptile",
            "Small animals, such as insects and crabs, and eggs",
            "New Guinea and northern Australia",
            "Forest and swamp",
            81,
            "https://upload.wikimedia.org/wikipedia/commons/d/d6/Varanus_prasinus.jpg",
            "Varanus prasinus",
            "3.3",
            "2.5",
            "20",
            "Green Tree Monitor",
            "0.7",
            "0.6"
        )

        val animalResponse = Animal()
        animalResponse.add(animalItem)
        

    }

}
