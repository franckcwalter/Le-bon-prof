package com.devid_academy.model.implementations.repositories

import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.LoginUserDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.ServerErrorMessage
import com.devid_academy.domain.entities.UserDto
import com.devid_academy.domain.repositories.UserRepository
import com.devid_academy.domain.utils.ApiHandler
import com.devid_academy.domain.utils.Resource
import com.devid_academy.model.network.ApiInterface

class UserRepositoryImpl (
    private val apiInterface: ApiInterface,
) : UserRepository, ApiHandler {

    override suspend fun logInUser(email : String, password : String): Resource<LoginUserDto> {
        return try {
            val response = apiInterface.logInUser(email, password)

            if (response.isSuccessful && response.body()?.status == 1)
                Resource.Success(response.body()!!)
            else
                Resource.Error(InfoMessage.EMAIL_OR_PASSWORD_INCORRECT.messageResId)
        } catch (e: Throwable) {
            Resource.Error(ServerErrorMessage.NO_SERVER_ANSWER.messageResId)
        }
    }

    override suspend fun createUser(createUser : CreateUserDto): Resource<LoginUserDto> {
        return try {
            val response = apiInterface.createUser(createUser)

            Resource.Success(response.body()!!)
            if (response.isSuccessful){
                when(response.body()?.status){
                    1 -> { Resource.Success(response.body()!!)}
                    -1 -> {Resource.Error(InfoMessage.ACCOUNT_ALREADY_EXISTS.messageResId)}
                    else -> { Resource.Error(InfoMessage.ACCOUNT_NOT_CREATED.messageResId)}
                }
            }else Resource.Error(InfoMessage.ACCOUNT_NOT_CREATED.messageResId)
        } catch (e: Throwable) {
            Resource.Error(ServerErrorMessage.NO_SERVER_ANSWER.messageResId)
        }
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