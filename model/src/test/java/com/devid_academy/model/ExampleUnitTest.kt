package com.devid_academy.model

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.FetchAdsUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.implementations.usecases.FetchAdsUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

