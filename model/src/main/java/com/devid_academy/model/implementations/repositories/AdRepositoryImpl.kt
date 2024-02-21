package com.devid_academy.model.implementations.repositories

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UpdateAdDto
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

    override suspend fun getAdById(adId: Long, userId: Long): Resource<AdDto> {
        return handleApi { apiInterface.getAd(adId, userId) }
    }

    override suspend fun getAdByUserId(idUser: Long): Resource<AdDto> {
        return handleApi { apiInterface.getAdByUserId(idUser) }
    }

    override suspend fun createAd(createAd: CreateAdDto): Resource<ResponseCreateAdDto> {
        return handleApi { apiInterface.createAd(createAd) }
    }

    override suspend fun toggleFav(idAd: Long, idUser: Long): Resource<ResponseDto> {
        return handleApi { apiInterface.toggleFav(idAd, idUser) }
    }

    override suspend fun updateAd(updateAd: UpdateAdDto): Resource<ResponseDto> {
        return handleApi { apiInterface.updateAd(updateAd) }
    }

    override suspend fun deleteAd(id: Long): Resource<ResponseDto> {
        return handleApi { apiInterface.deleteAd(id) }
    }
}