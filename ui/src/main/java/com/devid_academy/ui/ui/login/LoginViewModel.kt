package com.devid_academy.ui.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.usecases.LogInUserUseCase
import com.devid_academy.ui.R
import com.devid_academy.ui.util.SingleEvent
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
        if (email.isBlank() || password.isBlank())
            _userMessageLiveData.value =
                SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else viewModelScope.launch {

            logInUserUseCase.logInUser(email, password).let {

                it?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }

                if (logInUserUseCase.userWasLoggedIn) {
                    goToMain()
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