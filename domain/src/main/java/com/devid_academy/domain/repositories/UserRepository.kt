package com.devid_academy.domain.repositories

import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UserDto
import com.devid_academy.domain.utils.Resource

interface UserRepository {

    suspend fun getUsers() : Resource<List<UserDto>>
    suspend fun getUser() : Resource<UserDto>
    suspend fun updateUser() : Resource<ResponseDto>
    suspend fun deleteUser() : Resource<ResponseDto>

    suspend fun logInUser() : Resource<UserDto>
    suspend fun createUser() : Resource<UserDto>
}

