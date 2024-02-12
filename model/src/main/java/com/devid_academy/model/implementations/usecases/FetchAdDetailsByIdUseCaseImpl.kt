package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.FetchAdDetailsByIdUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchAdDetailsByIdUseCaseImpl(
    private val apiInterface: ApiInterface,
    private val myPrefs: MyPrefs,
    override var errorMessage: Int? = null
) : FetchAdDetailsByIdUseCase {
    override suspend fun fetchAdDetailsById(adId: Long): AdDto? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.getAd(adId, myPrefs.user_id)
                if (response?.isSuccessful == true) {
                    response.body()
                } else if (response?.body() == null){
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