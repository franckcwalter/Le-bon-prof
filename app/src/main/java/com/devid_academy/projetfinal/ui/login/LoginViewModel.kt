package com.devid_academy.projetfinal.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    fun goToRegister(){
        _navDirLiveData.value = SingleEvent(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    fun logInUserIfDataIsCorrect(email : String, password : String){

        if(email.isBlank() || password.isBlank())
            _userMessageLiveData.value = SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else viewModelScope.launch {
            withContext(Dispatchers.IO){
                apiInterface.logInUser(email, password)
            }.let {

                var userMessage : Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful){

                    val responseBody = it.body()!!

                    when (responseBody.status){
                        1 -> {
                            responseBody.user?.let {
                                myPrefs.user_id = it.id
                                myPrefs.user_role = it.idRole
                                myPrefs.user_name = it.firstName
                                goToMain()
                            }
                        }
                        0 -> userMessage = R.string.user_message_email_or_password_not_found
                    }
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }
            }
        }
    }

    private fun goToMain(){
        _navDirLiveData.value = SingleEvent(LoginFragmentDirections.actionLoginFragmentToMainFragment())

    }


}