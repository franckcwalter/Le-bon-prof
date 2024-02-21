package com.devid_academy.domain.repositories

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.utils.Resource

interface AdRepository {
    suspend fun getAds(id : Long) : Resource<AdsDto>
    suspend fun getAdById() : Resource<AdDto>
    suspend fun getAdByUserId() : Resource<AdDto>
    suspend fun createAd() : Resource<ResponseCreateAdDto>
    suspend fun toggleFav(): Resource<ResponseDto>
    suspend fun updateAd() : Resource<ResponseDto>
    suspend fun deleteAd() : Resource<ResponseDto>
}