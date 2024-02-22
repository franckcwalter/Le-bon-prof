package com.devid_academy.ui.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.usecases.RegisterUserUseCase
import com.devid_academy.domain.utils.AppRes
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.ui.R
import kotlinx.coroutines.launch

class RegisterViewModel(
    private var registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData: LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    fun registerUser(
        email : String,
        name : String,
        password : String,
        passwordConfirm : String,
        role : Int
    ){
        viewModelScope.launch {

            registerUserUseCase.registerUser(
                                    email ,
                                    name ,
                                    password ,
                                    passwordConfirm ,
                                    role
            ).let {

                when(it){
                    is Resource.Success -> {
                        goToMain()
                    }
                    is Resource.Error -> {
                        it.errorMessage?.let {
                            _userMessageLiveData.value = SingleEvent(it)
                        }
                    }
                }
            }
        }
    }

    private fun goToMain() {

        _navDirLiveData.value =
            SingleEvent(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
    }

}