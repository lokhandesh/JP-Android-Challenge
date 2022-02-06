package com.android.jpchallenge.feature.data.source.remote

import com.android.jpchallenge.core.api.RetrofitService
import com.android.jpchallenge.feature.domain.model.Animal
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AnimalDataSourceImplTest  {

    lateinit var animalDataSourceImpl: AnimalDataSourceImpl

    @Mock
    lateinit var animalDataSource: AnimalDataSource

    @Mock
    lateinit var retrofitService: RetrofitService

    private var animal = Animal()

    private lateinit var animalItem: Animal.AnimalItem

    @Before
    @Throws(Exception::class)
    fun setUp() {
        retrofitService = Mockito.mock(RetrofitService::class.java)
        animalDataSource = Mockito.mock(AnimalDataSource::class.java)
        animalDataSourceImpl = AnimalDataSourceImpl(retrofitService)

        animalItem = Animal.AnimalItem("Diurnal","Reptile","Small animals, such as insects and crabs, and eggs",
            "New Guinea and northern Australia","Forest and swamp",81,"https://upload.wikimedia.org/wikipedia/commons/d/d6/Varanus_prasinus.jpg",
            "Varanus prasinus","3.3","2.5","20","Green Tree Monitor","0.7","0.6")

        animal = Animal()
        animal.add(animalItem)
    }

    @Test
    fun testFetchAnimalList() {
        Mockito.doReturn(Single.just(animal)).`when`(retrofitService).fetchAnimalList()
        var result = animalDataSourceImpl.fetchAnimalList()
        assert(!result.blockingGet().isNullOrEmpty())
    }

    @Test
    fun testFetchAnimalListFail() {
        Mockito.doReturn(Single.just(Animal())).`when`(retrofitService).fetchAnimalList()
        var result = animalDataSourceImpl.fetchAnimalList()
        assert(result.blockingGet().isNullOrEmpty())
    }



}