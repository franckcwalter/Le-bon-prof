package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CreateAdUseCaseImpl(
    private val apiInterface: ApiInterface
) : CreateAdUseCase {

    override var adWasCreated: Boolean = false

    override suspend fun createAd(createAdDto: CreateAdDto): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.createAd(createAdDto)
                if (response?.isSuccessful == true) {

                    when (response.body()!!.status) {
                        1 -> {
                            adWasCreated = true
                            InfoMessage.AD_CREATED.messageResId
                        }

                        else -> {
                            ServerErrorMessage.AD_NOT_CREATED.messageResId
                        }
                    }
                } else if (response?.body() == null) {
                    ServerErrorMessage.SERVER_ANSWER_EMPTY.messageResId
                } else {
                    ServerErrorMessage.NO_SERVER_ANSWER.messageResId
                }
            } catch (e: Exception) {
                ServerErrorMessage.NO_SERVER_ANSWER.messageResId
            }
        }
    }
}
