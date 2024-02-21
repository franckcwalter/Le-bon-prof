package com.devid_academy.model.implementations.repositories

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.utils.ApiHandler
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface


class AdRepositoryImpl(
    private val apiInterface: ApiInterface,
) : AdRepository, ApiHandler {

    override suspend fun getAds(id : Long) : Resource<AdsDto> {

        return handleApi { apiInterface.getAds(id) }

    }

    override suspend fun getAdById(): Resource<AdDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getAdByUserId(): Resource<AdDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createAd(): Resource<ResponseCreateAdDto> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFav(): Resource<ResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateAd(): Resource<ResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAd(): Resource<ResponseDto> {
        TODO("Not yet implemented")
    }
}