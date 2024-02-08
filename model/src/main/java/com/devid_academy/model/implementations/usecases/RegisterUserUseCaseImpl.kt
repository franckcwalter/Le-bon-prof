package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.CreateUserDto
import com.devid_academy.domain.usecases.RegisterUserUseCase
import com.devid_academy.model.ApiInterface
import com.devid_academy.model.R
import com.devid_academy.projetfinal.util.MyPrefs
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

                        else -> {
                            R.string.user_message_email_or_password_not_found
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
