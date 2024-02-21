package com.devid_academy.domain.usecases
import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.utils.Resource

interface CreateAdUseCase {

    suspend fun createAd(title: String,
                         photo: String,
                         description: String,
                         place: String,
                         location : String,
                         price : String
    ): Resource<ResponseCreateAdDto>

}