package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.utils.Resource

interface FetchAdDetailsByIdUseCase {

    suspend fun fetchAdDetailsById(adId: Long): Resource<AdDto>

}