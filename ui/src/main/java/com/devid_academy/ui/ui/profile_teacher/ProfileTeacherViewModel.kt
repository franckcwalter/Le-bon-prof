package com.devid_academy.ui.ui.profile_teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.ButtonLabel
import com.devid_academy.domain.usecases.FetchAdDetailsByUserIdUseCase
import com.devid_academy.domain.usecases.GetUsernameUseCase
import com.devid_academy.domain.usecases.LogOutUserUseCase
import com.devid_academy.domain.utils.AppRes
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.ui.R
import kotlinx.coroutines.launch

class ProfileTeacherViewModel (
    private val getUsernameUseCase: GetUsernameUseCase,
    private val fetchAdDetail : FetchAdDetailsByUserIdUseCase,
    private val logOutUserUseCase: LogOutUserUseCase,
    private val appRes : AppRes,
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
        getUsernameUseCase.getUsername()?.let {
            _uiState.value = uiState.value?.copy(
                pageTitle = appRes.getString(R.string.profile_teacher_account_of, it)
            )
        }
    }

    fun fetchAd(){

        viewModelScope.launch {

            fetchAdDetail.fetchAdDetailsByUserId().let {

                when(it){
                    is Resource.Success -> {
                        it.data?.let { adDto ->

                            _uiState.value = uiState.value?.copy(
                                adTitle = adDto.title,
                                adPrice = adDto.price,
                                adContent = adDto.description,
                                buttonLabel = ButtonLabel.UPDATE_AD.messageResId,
                                hasNoAd = false,
                            )

                            this@ProfileTeacherViewModel.adDto = adDto
                        }
                    }
                    is Resource.Error -> {

                        it.errorMessage?.let {
                            _userMessageLiveData.value = SingleEvent(it)

                            _uiState.value = uiState.value?.copy(
                                adTitle = "",
                                adPrice = "",
                                adContent = "",
                                buttonLabel = ButtonLabel.CREATE_AD.messageResId,
                                hasNoAd = true,
                            )
                            adDto = null
                        }
                    }
                }
            }
        }
    }

    fun logOutUser(){

        logOutUserUseCase.logoutUser()

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