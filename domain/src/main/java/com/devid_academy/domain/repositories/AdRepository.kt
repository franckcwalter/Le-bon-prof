package com.devid_academy.domain.repositories

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.utils.Resource

interface AdRepository {
    suspend fun getAds(id : Long) : Resource<AdsDto>
    suspend fun getAdById(adId: Long, userId: Long) : Resource<AdDto>
    suspend fun getAdByUserId(idUser : Long) : Resource<AdDto>
    suspend fun createAd(createAd : CreateAdDto) : Resource<ResponseCreateAdDto>
    suspend fun toggleFav(idAd : Long, idUser : Long): Resource<ResponseDto>
    suspend fun updateAd(updateAd : UpdateAdDto) : Resource<ResponseDto>
    suspend fun deleteAd(id : Long) : Resource<ResponseDto>
}