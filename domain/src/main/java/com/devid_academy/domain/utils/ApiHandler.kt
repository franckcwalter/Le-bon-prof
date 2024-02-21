package com.devid_academy.domain.utils

import com.devid_academy.domain.entities.ServerErrorMessage
import retrofit2.Response

interface ApiHandler {
    suspend fun <T : Any> handleApi(execute: (suspend () -> Response<T>)) : Resource<T>
    {
        return try {
            val response = execute()

            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(ServerErrorMessage.NO_SERVER_ANSWER.messageResId)
            }
        } catch (e: Throwable) {
            Resource.Error(ServerErrorMessage.NO_SERVER_ANSWER.messageResId)
        }
    }
}
