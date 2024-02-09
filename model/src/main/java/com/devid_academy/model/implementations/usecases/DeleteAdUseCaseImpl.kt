package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAdUseCaseImpl(
    private val apiInterface: ApiInterface,
) : DeleteAdUseCase {

    override var adWasDeleted: Boolean = false

    override suspend fun deleteAd(idAd: Long): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.deleteAd(idAd)
                if (response?.isSuccessful == true) {

                    when (response.body()!!.status) {
                        1 -> {
                            adWasDeleted = true
                            InfoMessage.AD_DELETED.messageResId
                        }

                        else -> {
                            ServerErrorMessage.AD_NOT_DELETED.messageResId
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