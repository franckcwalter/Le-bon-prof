package com.devid_academy.ui.ui.ad_update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.domain.utils.SingleEvent
import com.devid_academy.ui.R
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

        if (title.isBlank()
            || photo.isBlank()
            || description.isBlank()
            || place.isBlank()
            || location.isBlank()
            || price.isBlank()
        )
            _userMessageLiveData.value =
                SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else {

            viewModelScope.launch {

                updateAdUseCase.updateAd(
                    UpdateAdDto(
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
                    )
                )?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }

                if(updateAdUseCase.adWasUpdated){
                    _adWasUpdatedLiveData.value = SingleEvent(true)
                }
            }
        }
    }

    fun deleteAd(idAd : Long) {

        viewModelScope.launch {

            deleteAdUseCase.deleteAd(idAd)?.let{
                _userMessageLiveData.value = SingleEvent(it)
            }

            if(deleteAdUseCase.adWasDeleted){
                _adWasUpdatedLiveData.value = SingleEvent(true)
            }
        }
    }
}