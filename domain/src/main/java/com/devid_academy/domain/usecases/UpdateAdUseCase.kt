package com.devid_academy.domain.usecases
import com.devid_academy.domain.entities.UpdateAdDto


interface UpdateAdUseCase {

    var adWasUpdated : Boolean

    suspend fun updateAd(updateAdDto: UpdateAdDto): Int?

}