package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.LoginUserDto
import com.devid_academy.domain.utils.Resource

interface RegisterUserUseCase {

    suspend fun registerUser(
        email : String,
        name : String,
        password : String,
        passwordConfirm : String,
        role : Int
    ): Resource<LoginUserDto>

}

