package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.repositories.AdRepository
import com.devid_academy.domain.usecases.ToggleFavUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToggleFavUseCaseImpl(
    private val adRepository: AdRepository,
    private val myPrefs: MyPrefs
) : ToggleFavUseCase {

    override suspend fun toggleFav(idAd: Long): Resource<ResponseDto> {
        return adRepository.toggleFav(idAd, myPrefs.user_id)

        /*
        withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.toggleFav(idAd, myPrefs.user_id)
                if (response?.isSuccessful == true) {

                    when (response.body()!!.status) {
                        1 -> {
                            favIsToggled = true
                            InfoMessage.FAV_STATUS_MODIFIED.messageResId
                        }

                        else -> {
                            ServerErrorMessage.FAV_STATUS_NOT_MODIFIED.messageResId
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
        }*/
    }
}
