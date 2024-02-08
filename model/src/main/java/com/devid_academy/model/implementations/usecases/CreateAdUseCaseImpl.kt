package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
import com.devid_academy.domain.CreateAdDto
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
                        "1" -> {
                            adWasCreated = true
                            R.string.ad_was_created
                        }

                        else -> {
                            R.string.ad_could_not_be_created
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
