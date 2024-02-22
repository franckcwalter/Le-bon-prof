package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.LoginUserDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.repositories.UserRepository
import com.devid_academy.domain.usecases.LogInUserUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.model.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LogInUserUseCaseImpl(
    private var userRepository: UserRepository,
    private var myPrefs: MyPrefs
) : LogInUserUseCase {

    override suspend fun logInUser(email: String, password: String): Resource<LoginUserDto> {

        return if (email.isBlank() || password.isBlank())
            Resource.Error(InfoMessage.FILL_OUT_ALL_FIELDS.messageResId)
        else userRepository.logInUser(email, password).also {
                if (it is Resource.Success){
                    it.data?.user?.let {
                        myPrefs.user_id = it.id
                        myPrefs.user_role = it.idRole
                        myPrefs.user_name = it.firstName
                    }
                }
            }
    }
}