package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.FetchAdDetailsByIdUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource

class FetchAdDetailsByIdUseCaseImpl(
    private val adRepository: AdRepository,
    private val myPrefs: MyPrefs,
) : FetchAdDetailsByIdUseCase {
    override suspend fun fetchAdDetailsById(adId: Long): Resource<AdDto> {
        return adRepository.getAdById(adId, myPrefs.user_id)
    }
}
