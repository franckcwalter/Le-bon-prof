package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.InfoMessage
import com.devid_academy.domain.ServerErrorMessage
import com.devid_academy.domain.usecases.ToggleFavUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToggleFavUseCaseImpl(
    private val apiInterface: ApiInterface,
    private val myPrefs: MyPrefs
) : ToggleFavUseCase {

    override var favIsToggled: Boolean = false

    override suspend fun toggleFav(idAd: Long): Int? {
        return withContext(Dispatchers.IO) {
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
        }
    }
}
