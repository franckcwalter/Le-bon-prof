package com.devid_academy.domain.usecases
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.utils.Resource


interface UpdateAdUseCase {

    suspend fun updateAd(id : Long,
                         reference : String,
                         title : String,
                         photo : String,
                         price : String,
                         location : String,
                         place : String,
                         description : String,
                         createdAt : String,
                         approved : Int,
                         idUser : Long
    ): Resource<ResponseDto>

}