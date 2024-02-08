package com.devid_academy.domain.usecases
import com.devid_academy.domain.CreateAdDto

interface CreateAdUseCase {

    var adWasCreated : Boolean

    suspend fun createAd(createAdDto: CreateAdDto): Int?

}