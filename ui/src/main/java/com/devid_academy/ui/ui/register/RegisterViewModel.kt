package com.devid_academy.projetfinal.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.ui.network.CreateUserDto
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData


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
        }
        else viewModelScope.launch {

                withContext(Dispatchers.IO){
                    apiInterface.createUser(CreateUserDto(email,name,password,role))

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
                            -1 -> userMessage = R.string.user_message_account_already_exists
                            0 -> userMessage = R.string.user_message_account_not_created
                        }
                    }

                    userMessage?.let {
                        _userMessageLiveData.value = SingleEvent(it)
                    }


                }
            }
        }

    fun goToMain(){
        _navDirLiveData.value = SingleEvent(RegisterFragmentDirections.actionRegisterFragmentToMainFragment())
    }

}