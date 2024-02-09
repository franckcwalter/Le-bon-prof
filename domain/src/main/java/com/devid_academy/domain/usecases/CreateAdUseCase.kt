package com.devid_academy.domain.usecases
import com.devid_academy.domain.entities.CreateAdDto

interface CreateAdUseCase {

    var adWasCreated : Boolean

    suspend fun createAd(createAdDto: CreateAdDto): Int?

}