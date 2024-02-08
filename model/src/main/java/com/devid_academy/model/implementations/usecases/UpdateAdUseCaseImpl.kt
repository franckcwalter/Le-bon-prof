package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
import com.devid_academy.domain.UpdateAdDto
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
                        "1" -> {
                            adWasUpdated = true
                            R.string.ad_was_updated
                        }

                        else -> {
                            R.string.ad_could_not_be_updated
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