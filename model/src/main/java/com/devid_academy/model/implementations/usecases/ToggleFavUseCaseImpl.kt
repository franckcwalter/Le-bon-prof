package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.ToggleFavUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
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
                        "1" -> {
                            favIsToggled = true
                            R.string.fav_status_modified
                        }

                        else -> {
                            R.string.fav_status_not_modified
                        }
                    }
                } else if (response?.body() == null) {
                    R.string.user_message_server_answer_empty
                } else {
                    R.string.user_message_no_server_answer
                }
            } catch (e: Exception) {
                R.string.user_message_no_server_answer
            }
        }
    }
}
