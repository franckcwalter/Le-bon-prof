package com.devid_academy.domain.usecases

import com.devid_academy.domain.AdDto

interface FetchAdDetailsByUserIdUseCase {

    var errorMessage : Int?

    suspend fun fetchAdDetailsByUserId(): AdDto?

}

