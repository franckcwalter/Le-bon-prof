package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.AdsDto

interface FetchAdsUseCase {

    var errorMessage: Int?

    suspend fun fetchAds(): AdsDto?

}