package com.devid_academy.domain.usecases

import com.devid_academy.domain.AdDto

interface FetchAdDetailsByIdUseCase {

    var errorMessage : Int?

    suspend fun fetchAdDetailsById(adId: Long): AdDto?

}



