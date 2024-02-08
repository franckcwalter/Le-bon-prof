package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.FetchAdDetailsByUserIdUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
import com.devid_academy.domain.AdDto
import com.devid_academy.projetfinal.util.MyPrefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchAdDetailsByUserIdUseCaseImpl(
    private val apiInterface: ApiInterface,
    private val myPrefs: MyPrefs,
    override var errorMessage: Int? = null
) : FetchAdDetailsByUserIdUseCase {

    override suspend fun fetchAdDetailsByUserId(): AdDto? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.getAdByUserId(myPrefs.user_id)
                if (response?.isSuccessful == true) {
                    response.body()
                } else if (response?.body() == null){
                    errorMessage = R.string.user_message_server_answer_empty
                    null
                } else {
                    errorMessage = R.string.user_message_no_server_answer
                    null
                }
            } catch (e: Exception) {
                errorMessage = R.string.user_message_no_server_answer
                null
            }
        }
    }
}