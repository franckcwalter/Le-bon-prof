package com.devid_academy.projetfinal.ui.ad_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.domain.CreateAdDto
import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID

class AdCreateViewModel(
    private var createAdUseCase : CreateAdUseCase,
    private var myPrefs : MyPrefs
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
        if(title.isBlank()
            || photo.isBlank()
            || description.isBlank()
            || place.isBlank()
            || location.isBlank()
            || price.isBlank())
            _userMessageLiveData.value = SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else {

            viewModelScope.launch {

                createAdUseCase.createAd(
                    CreateAdDto(
                        UUID.randomUUID().toString(),
                        title,
                        photo,
                        description,
                        place,
                        location,
                        price,
                        Calendar.getInstance().time.toString(),
                        0,
                        myPrefs.user_id
                    ) //TODO : enlever my pref, le mettre dans le use case (?)
                )?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }

                if (createAdUseCase.adWasCreated){
                         _navBackLiveData.value = SingleEvent(true)
                }
            }
        }
    }
}