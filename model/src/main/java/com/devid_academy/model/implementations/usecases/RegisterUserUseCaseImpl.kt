package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.usecases.RegisterUserUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterUserUseCaseImpl(
    private val apiInterface: ApiInterface,
    private val myPrefs: MyPrefs
) : RegisterUserUseCase {

    override var userWasRegistered = false

    override suspend fun registerUser(createUserDto: CreateUserDto): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiInterface.createUser(createUserDto)
                if (response?.isSuccessful == true) {

                    val responseBody = response.body()!!

                    when (responseBody.status) {
                        1 -> {
                            responseBody.user?.let {
                                myPrefs.user_id = it.id
                                myPrefs.user_role = it.idRole
                                myPrefs.user_name = it.firstName
                                userWasRegistered = true
                            }
                            null
                        }

                        -1 -> InfoMessage.ACCOUNT_ALREADY_EXISTS.messageResId
                        else -> InfoMessage.ACCOUNT_NOT_CREATED.messageResId

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
