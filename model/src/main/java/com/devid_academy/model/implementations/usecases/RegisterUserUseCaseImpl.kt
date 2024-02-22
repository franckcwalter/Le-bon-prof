package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.entities.LoginUserDto
import com.devid_academy.domain.repositories.UserRepository
import com.devid_academy.domain.usecases.RegisterUserUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource

class RegisterUserUseCaseImpl(
    private val userRepository: UserRepository,
    private val myPrefs: MyPrefs
) : RegisterUserUseCase {

    override suspend fun registerUser(email : String,
                                      name : String,
                                      password : String,
                                      passwordConfirm : String,
                                      role : Int
    ): Resource<LoginUserDto> {

        return  if(email.isBlank()
            || name.isBlank()
            || password.isBlank()
            || passwordConfirm.isBlank())
            Resource.Error(InfoMessage.FILL_OUT_ALL_FIELDS.messageResId)
        else if (password != passwordConfirm){
            Resource.Error(InfoMessage.PASSWORDS_DONT_MATCH.messageResId)
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$".toRegex())){
            Resource.Error(InfoMessage.PASSWORD_SPECS_NOT_RESPECTED.messageResId)
        } else

            userRepository.createUser(CreateUserDto(
                                            email,
                                            password,
                                            name,
                                            role)
        ).also {
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
