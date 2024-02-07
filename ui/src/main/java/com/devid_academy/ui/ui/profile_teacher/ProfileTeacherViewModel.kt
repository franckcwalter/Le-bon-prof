package com.devid_academy.projetfinal.ui.profile_teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.AdDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.AppResImpl
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileTeacherViewModel @Inject constructor(
    private val appRes : AppResImpl,
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel(){

    private val _uiState = MutableLiveData(ProfileTeacherUiState())
    val uiState : LiveData<ProfileTeacherUiState>
        get() = _uiState

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    private var adDto : AdDto? = null

    init{
        setName()
        // set initial state ?
    }

    private fun setName(){
        _uiState.value = uiState.value?.copy(
            pageTitle = appRes.getString(R.string.profile_teacher_account_of, myPrefs.user_name!!),
        )
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

                    val ad = it.body()!!

                    _uiState.value = uiState.value?.copy(
                        adTitle = ad.title,
                        adPrice = ad.price,
                        adContent = ad.description,
                        buttonLabel = appRes.getString(R.string.update_ad),
                        hasNoAd = false,
                    )
                    adDto = ad
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)

                    _uiState.value = uiState.value?.copy(
                        buttonLabel = appRes.getString(R.string.create_ad),
                        adTitle = "",
                        adPrice = "",
                        adContent = "",
                        hasNoAd = true,
                    )
                    adDto = null
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

    fun goToCreateOrUpdateAd() {

        (if (adDto != null){
                SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdUpdateFragment2(adDto!!))
        } else SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdCreateFragment()))
            .let {
                _navDirLiveData.value = it
            }
    }
}