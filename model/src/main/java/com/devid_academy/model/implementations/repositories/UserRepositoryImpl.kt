package com.devid_academy.model.implementations.repositories

import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.UserDto
import com.devid_academy.domain.repositories.UserRepository
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface

class UserRepositoryImpl (
    private val apiInterface: ApiInterface,
) : UserRepository {

    override suspend fun logInUser(email : String, password : String): Resource<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun createUser(createUser : CreateUserDto): Resource<UserDto> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(): Resource<List<UserDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): Resource<UserDto> {
        TODO("Not yet implemented")
    }


    override suspend fun updateUser(): Resource<ResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): Resource<ResponseDto> {
        TODO("Not yet implemented")
    }

}