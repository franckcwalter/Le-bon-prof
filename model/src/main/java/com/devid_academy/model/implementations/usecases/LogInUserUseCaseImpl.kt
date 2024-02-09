package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.LogInUserUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInUserUseCaseImpl(
    private var apiInterface: ApiInterface,
    private var myPrefs: MyPrefs
) : LogInUserUseCase {

    override var userWasLoggedIn: Boolean = false

    override suspend fun logInUser(email: String, password: String): Int? {

        return withContext(Dispatchers.IO) {

            try {
                val response = apiInterface.logInUser(email, password)
                if (response?.isSuccessful == true) {

                    val responseBody = response.body()!!

                    when (responseBody.status) {
                        1 -> {
                            responseBody.user?.let {
                                myPrefs.user_id = it.id
                                myPrefs.user_role = it.idRole
                                myPrefs.user_name = it.firstName
                                userWasLoggedIn = true
                            }
                            null
                        }

                        else -> {
                            InfoMessage.EMAIL_OR_PASSWORD_INCORRECT.messageResId
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