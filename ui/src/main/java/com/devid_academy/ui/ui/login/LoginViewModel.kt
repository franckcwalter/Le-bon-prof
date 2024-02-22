package com.devid_academy.ui.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.usecases.LogInUserUseCase
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.ui.R
import kotlinx.coroutines.launch

class LoginViewModel(
    private var logInUserUseCase: LogInUserUseCase
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData: LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    fun logInUserIfDataIsCorrect(
        email: String,
        password: String
    ) {

        viewModelScope.launch {

            logInUserUseCase.logInUser(email, password).let {

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

    private fun goToMain(){
        _navDirLiveData.value = SingleEvent(LoginFragmentDirections.actionLoginFragmentToMainFragment())

    }

    fun goToRegister(){
        _navDirLiveData.value =
            SingleEvent(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }
}