package com.devid_academy.ui

import com.devid_academy.domain.entities.AdDto
import org.junit.Test

import org.junit.Assert.*

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

    @Test
    fun substraction(){
        assertEquals(1, 2-1)
    }
}


class ValidatorTest {

    @Test
    fun whenInputIsValid() {

        val amount = 100
        val desc = "random desc"
        val result = Validator.validateInput(amount, desc)

        assertEquals(result, true)
    }

    @Test
    fun whenInputIsInvalidBecauseAmount(){

        val amount = -1
        val desc = "desc"
        val result = Validator.validateInput(amount, desc)

        assertEquals(result, false)
    }

    @Test
    fun whenInputIsInvalidBecauseDesc(){

        val amount = 100
        val desc = ""
        val result = Validator.validateInput(amount, desc)

        assertEquals(result, false)
    }

    @Test
    fun returnHello(){

        val name = "Bonjour"
        val result = Validator.bonjour()

        assertEquals(name, result)

    }

    @Test
    fun returnsAdDto() {

        val name = AdDto(1,"","","","","","","","",0, 1, "",1)
        val result = Validator.fetchAdDto()

        assertEquals(name,result)
    }
}