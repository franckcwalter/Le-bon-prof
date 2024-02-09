package com.devid_academy.ui.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.CreateUserDto
import com.devid_academy.domain.usecases.RegisterUserUseCase
import com.devid_academy.ui.R
import com.devid_academy.ui.util.SingleEvent
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
        if(email.isBlank()
            || name.isBlank()
            || password.isBlank()
            || passwordConfirm.isBlank())
            _userMessageLiveData.value = SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else if (password != passwordConfirm){
            _userMessageLiveData.value = SingleEvent(R.string.user_message_passwords_dont_match)
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$".toRegex())){
            _userMessageLiveData.value = SingleEvent(R.string.user_message_password_specs)
        } else viewModelScope.launch {

            registerUserUseCase.registerUser(CreateUserDto(email, name, password, role)).let {

                it?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }

                if (registerUserUseCase.userWasRegistered) {
                    goToMain()
                }
            }
        }
    }

    private fun goToMain() {

        _navDirLiveData.value =
            SingleEvent(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
    }

}