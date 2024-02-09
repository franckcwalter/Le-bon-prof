package com.devid_academy.domain.usecases

import com.devid_academy.domain.entities.CreateUserDto

interface RegisterUserUseCase {

    var userWasRegistered: Boolean

    suspend fun registerUser(createUserDto: CreateUserDto): Int?

}

