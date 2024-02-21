package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.utils.Resource

interface FetchAdsUseCase {

    suspend fun fetchAds(): Resource<AdsDto>

}