package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateAdUseCaseImpl(
    private val apiInterface: ApiInterface
) : UpdateAdUseCase {

    override var adWasUpdated: Boolean = false

    override suspend fun updateAd(updateAdDto: UpdateAdDto): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.updateAd(updateAdDto)
                if (response?.isSuccessful == true) {

                    when (response.body()!!.status) {
                        1 -> {
                            adWasUpdated = true
                            InfoMessage.AD_UPDATED.messageResId
                        }

                        else -> {
                            ServerErrorMessage.AD_NOT_UPDATED.messageResId
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