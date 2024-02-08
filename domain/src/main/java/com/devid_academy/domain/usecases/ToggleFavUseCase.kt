package com.devid_academy.domain.usecases

interface ToggleFavUseCase {

    val favIsToggled: Boolean

    suspend fun toggleFav(idAd: Long): Int?

}