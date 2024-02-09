package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.FetchAdsUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchAdsUseCaseImpl(
    private val apiInterface: ApiInterface,
    private val myPrefs: MyPrefs
) : FetchAdsUseCase {

    override var errorMessage: Int? = null

    override suspend fun fetchAds(): AdsDto? {

        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.getAds(myPrefs.user_id)
                if (response?.isSuccessful == true) {
                    response.body()
                } else if (response?.body() == null) {
                    errorMessage = ServerErrorMessage.SERVER_ANSWER_EMPTY.messageResId
                    null

                } else {
                    errorMessage = ServerErrorMessage.NO_SERVER_ANSWER.messageResId
                    null
                }
            } catch (e: Exception) {
                errorMessage = ServerErrorMessage.NO_SERVER_ANSWER.messageResId
                null
            }
        }
    }
}

