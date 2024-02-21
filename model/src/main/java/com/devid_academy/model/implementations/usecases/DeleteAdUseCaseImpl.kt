package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DeleteAdUseCaseImpl(
    private val adRepository : AdRepository,
) : DeleteAdUseCase {

    override suspend fun deleteAd(idAd: Long): Resource<ResponseDto> {
        return adRepository.deleteAd(idAd)
    }
}