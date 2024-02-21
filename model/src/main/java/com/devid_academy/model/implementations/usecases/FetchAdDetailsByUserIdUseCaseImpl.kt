package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.FetchAdDetailsByUserIdUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchAdDetailsByUserIdUseCaseImpl(
    private val adRepository: AdRepository,
    private val myPrefs: MyPrefs,
) : FetchAdDetailsByUserIdUseCase {

    override suspend fun fetchAdDetailsByUserId(): Resource<AdDto> {
        return adRepository.getAdByUserId(myPrefs.user_id)
    }
}