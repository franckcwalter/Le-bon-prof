package com.devid_academy.projetfinal.ui.profile_teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.network.AdDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.ui.profile_learner.ProfileLearnerFragmentDirections
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileTeacherViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel(){

    private var _userNameLiveData = MutableLiveData<String>()
    val userNameLiveData : LiveData<String> get() = _userNameLiveData

    private var _adLiveData = MutableLiveData<AdDto?>()
    val adLiveData : LiveData<AdDto?> get() = _adLiveData


    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    init{
        setName()
    }

    fun setName(){
        _userNameLiveData.value = myPrefs.user_name
    }

    fun fetchAd(){

        viewModelScope.launch {
            withContext(Dispatchers.IO){
                apiInterface.getAdfromUserId(myPrefs.user_id)

            }.let {

                var userMessage: Int? = null

                if (it == null){
                    userMessage = R.string.user_message_no_server_answer
                }
                else if (it.body() == null){
                    userMessage = R.string.user_message_server_answer_empty
                }
                else if (it.isSuccessful) {
                    _adLiveData.value = it.body()!!
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                    _adLiveData.value = null
                }
            }
        }
    }

    fun logOutUser(){

        myPrefs.user_id = 0
        myPrefs.user_role = 0

        _navDirLiveData.value =
            SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToLoginFragment())
    }

    fun goToCreateOrUpdateAd(userHasPostedAd : Boolean) {

        (if (userHasPostedAd){
            adLiveData.value?.let {
                SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdUpdateFragment2(it))
            }
        } else SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdCreateFragment()))
            .let {
                _navDirLiveData.value = it
            }
    }
}