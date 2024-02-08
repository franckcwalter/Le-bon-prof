package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
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
                        "1" -> {
                            adWasDeleted = true
                            R.string.ad_was_deleted
                        }
                        else -> {
                            R.string.ad_could_not_be_deleted
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