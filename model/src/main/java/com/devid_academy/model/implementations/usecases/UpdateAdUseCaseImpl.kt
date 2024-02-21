package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.domain.utils.Resource

class UpdateAdUseCaseImpl(
    private val adRepository: AdRepository
) : UpdateAdUseCase {

    override suspend fun updateAd(id : Long,
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
    ): Resource<ResponseDto> {
        return if (title.isBlank()
                    || photo.isBlank()
                    || description.isBlank()
                    || place.isBlank()
                    || location.isBlank()
                    || price.isBlank()) {
                Resource.Error(InfoMessage.FILL_OUT_ALL_FIELDS.messageResId)
            } else {
            adRepository.updateAd(
                UpdateAdDto(
                    id,
                    reference,
                    title,
                    photo,
                    description,
                    place,
                    location,
                    price,
                    createdAt,
                    approved,
                    idUser
            )
            )
        }
    }
}