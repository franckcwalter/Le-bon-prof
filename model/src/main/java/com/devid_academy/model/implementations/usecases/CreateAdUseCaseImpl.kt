package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.UUID

class CreateAdUseCaseImpl(
    private val adRepository: AdRepository,
    private val myPrefs: MyPrefs
) : CreateAdUseCase {
    override suspend fun createAd(title: String,
                                  photo: String,
                                  description: String,
                                  place: String,
                                  location : String,
                                  price : String)
    : Resource<ResponseCreateAdDto> {

        return if(title.isBlank()
                || photo.isBlank()
                || description.isBlank()
                || place.isBlank()
                || location.isBlank()
                || price.isBlank()){
            Resource.Error(InfoMessage.FILL_OUT_ALL_FIELDS.messageResId)
        } else {
            adRepository.createAd(
                CreateAdDto(
                    UUID.randomUUID().toString(),
                    title,
                    photo,
                    description,
                    place,
                    location,
                    price,
                    Calendar.getInstance().time.toString(),
                    0,
                    myPrefs.user_id)
            )
        }
    }
}