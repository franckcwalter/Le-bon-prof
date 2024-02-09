package com.devid_academy.ui.ui.profile_teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.usecases.FetchAdDetailsByUserIdUseCase
import com.devid_academy.domain.utils.AppRes
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.ui.R
import kotlinx.coroutines.launch

class ProfileTeacherViewModel (
    private val fetchAdDetail : FetchAdDetailsByUserIdUseCase,
    private val appRes : AppRes,
    private var myPrefs : MyPrefs
) : ViewModel() {

    private val _uiState = MutableLiveData(ProfileTeacherUiState())
    val uiState : LiveData<ProfileTeacherUiState>
        get() = _uiState

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    private var adDto : AdDto? = null

    init {
        setName()
    }

    private fun setName(){
        _uiState.value = uiState.value?.copy(
            pageTitle = appRes.getString(R.string.profile_teacher_account_of, myPrefs.user_name!!),
        )
    }

    fun fetchAd(){

        viewModelScope.launch {

            fetchAdDetail.fetchAdDetailsByUserId().let {

                fetchAdDetail.errorMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)

                    _uiState.value = uiState.value?.copy(
                        adTitle = "",
                        adPrice = "",
                        adContent = "",
                        buttonLabel = appRes.getString(R.string.create_ad),
                        hasNoAd = true,
                    )
                    adDto = null
                }

                it?.let { adDto ->

                    _uiState.value = uiState.value?.copy(
                        adTitle = adDto.title,
                        adPrice = adDto.price,
                        adContent = adDto.description,
                        buttonLabel = appRes.getString(R.string.update_ad),
                        hasNoAd = false,
                    )

                    this@ProfileTeacherViewModel.adDto = adDto
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