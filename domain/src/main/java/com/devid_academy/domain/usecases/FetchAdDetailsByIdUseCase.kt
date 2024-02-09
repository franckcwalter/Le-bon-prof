package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdDto

interface FetchAdDetailsByIdUseCase {

    var errorMessage : Int?

    suspend fun fetchAdDetailsById(adId: Long): AdDto?

}



