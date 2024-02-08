package com.devid_academy.domain.usecases

interface DeleteAdUseCase {

    var adWasDeleted : Boolean

    suspend fun deleteAd(idAd: Long): Int?

}