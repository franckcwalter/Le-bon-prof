package com.devid_academy.domain

import com.devid_academy.projetfinal.network.AdDto

interface FetchAdDetailsUseCase {

    var errorMessage : Int?

    suspend fun fetchAdDetails(adId: Long): AdDto?

    fun displayErrorMessage(): Int?

}

