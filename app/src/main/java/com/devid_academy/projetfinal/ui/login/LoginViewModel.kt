package com.devid_academy.projetfinal.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


    fun goToRegister(){
        _navDirLiveData.value = SingleEvent(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
    }

    fun logInUser(){

        /** TODO : log user in with api  **/

        _navDirLiveData.value = SingleEvent(LoginFragmentDirections.actionLoginFragmentToMainFragment())

    }


}