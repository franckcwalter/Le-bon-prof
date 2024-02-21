package com.devid_academy.ui.ui.ad_update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import kotlinx.coroutines.launch


class AdUpdateViewModel (
    private var updateAdUseCase : UpdateAdUseCase,
    private var deleteAdUseCase : DeleteAdUseCase
) : ViewModel() {

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    private var _adWasUpdatedLiveData = MutableLiveData<SingleEvent<Boolean>>()
    val adWasUpdatedLiveData : LiveData<SingleEvent<Boolean>> get() = _adWasUpdatedLiveData

    fun updateAd(
        id : Long,
        reference : String,
        title : String,
        photo : String,
        price : String,
        location : String,
        place : String,
        description : String,
        createdAt : String,
        approved : Int,
        idUser : Long
    ) {

        viewModelScope.launch {

            updateAdUseCase.updateAd(
                    id,
                    reference,
                    title,
                    photo,
                    description,
                    place,
                    location,
                    price,
                    createdAt,
                    approved,
                    idUser
            ).let {

                when(it){
                    is Resource.Success -> {
                        _userMessageLiveData.value = SingleEvent(InfoMessage.AD_UPDATED.messageResId)
                        _adWasUpdatedLiveData.value = SingleEvent(true)
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

    fun deleteAd(idAd : Long) {

        viewModelScope.launch {

            deleteAdUseCase.deleteAd(idAd).let{

                when(it){
                    is Resource.Success -> {
                        _adWasUpdatedLiveData.value = SingleEvent(true)
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