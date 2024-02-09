package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdDto

interface FetchAdDetailsByUserIdUseCase {

    var errorMessage : Int?

    suspend fun fetchAdDetailsByUserId(): AdDto?

}

