package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.FetchAdsUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource

class FetchAdsUseCaseImpl(
    private val adRepository: AdRepository,
    private val myPrefs: MyPrefs
) : FetchAdsUseCase {

    override suspend fun fetchAds(): Resource<AdsDto> {
        return adRepository.getAds(myPrefs.user_id)
    }
}