package com.android.jpchallenge.feature.data.repository

import com.android.jpchallenge.core.api.RetrofitService
import com.android.jpchallenge.feature.data.source.remote.AnimalDataSource
import com.android.jpchallenge.feature.data.source.remote.AnimalDataSourceImpl
import com.android.jpchallenge.feature.domain.model.Animal
import dagger.hilt.components.SingletonComponent
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnimalRepositoryImpTest {

    lateinit var animalRepositoryImp: AnimalRepositoryImp

    @Mock
    lateinit var animalDataSource: AnimalDataSource

    private var animal = Animal()

    private lateinit var animalItem: Animal.AnimalItem

    @Before
    @Throws(Exception::class)
    fun setUp() {
        animalDataSource = Mockito.mock(AnimalDataSource::class.java)
        animalRepositoryImp = AnimalRepositoryImp(animalDataSource)

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
        animal.add(animalItem)

    }

    @Test
    fun testFetchAnimalListSuccess() {
        Mockito.doReturn(Single.just(animal)).`when`(animalDataSource).fetchAnimalList()
        var result = animalRepositoryImp.fetchAnimalList()
        assert(!result.blockingGet().isNullOrEmpty())
    }
    @Test
    fun testFetchAnimalListFail() {
        Mockito.doReturn(Single.just(Animal())).`when`(animalDataSource).fetchAnimalList()
        var result = animalRepositoryImp.fetchAnimalList()
        assert(result.blockingGet().isNullOrEmpty())
    }


}