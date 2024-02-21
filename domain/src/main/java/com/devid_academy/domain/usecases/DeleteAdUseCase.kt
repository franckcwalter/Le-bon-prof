package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.utils.Resource

interface DeleteAdUseCase {

    suspend fun deleteAd(idAd: Long): Resource<ResponseDto>

}