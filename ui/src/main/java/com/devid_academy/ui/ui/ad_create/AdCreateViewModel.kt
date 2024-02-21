package com.devid_academy.ui.ui.ad_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import kotlinx.coroutines.launch

class AdCreateViewModel(
    private var createAdUseCase : CreateAdUseCase,
) : ViewModel() {

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    private var _navBackLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val navBackLiveData : LiveData<SingleEvent<Boolean>> get() = _navBackLiveData

    fun createAd (
        title : String,
        photo : String,
        description : String,
        place : String,
        location : String,
        price : String
    ) {

        viewModelScope.launch {

            createAdUseCase.createAd(
                    title,
                    photo,
                    description,
                    place,
                    location,
                    price
            ).let {

                when(it){
                    is Resource.Success -> {
                        _userMessageLiveData.value = SingleEvent(InfoMessage.AD_CREATED.messageResId)
                        _navBackLiveData.value = SingleEvent(true)
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
}