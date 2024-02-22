package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.LoginUserDto
import com.devid_academy.domain.utils.Resource

interface LogInUserUseCase {

    suspend fun logInUser(email: String, password: String): Resource<LoginUserDto>

}