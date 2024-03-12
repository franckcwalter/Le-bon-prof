package com.devid_academy.ui

import com.devid_academy.domain.entities.AdDto

object Validator {

    fun validateInput(amount:Int, desc: String) : Boolean = !(amount <= 0 || desc.isEmpty())

    fun bonjour() : String = "Bonjour"

    fun fetchAdDto() : AdDto = AdDto(1,"","","","","","","","",0, 1, "",1)

}