package com.devid_academy.projetfinal.ui.ad_update

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.network.CreateAdDto
import com.devid_academy.projetfinal.network.SubjectDto
import com.devid_academy.projetfinal.network.UpdateAdDto
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.Place
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdUpdateViewModel @Inject constructor(
    private var apiInterface : ApiInterface
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
    ){

        if(title.isBlank()
            || photo.isBlank()
            || description.isBlank()
            || place.isBlank()
            || location.isBlank()
            || price.isBlank())
            _userMessageLiveData.value = SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else {

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    apiInterface.updateAd(
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
                    )

                }.let {

                    var userMessage : Int? = null

                    if (it == null)
                        userMessage = R.string.user_message_no_server_answer
                    else if (it.body() == null)
                        userMessage = R.string.user_message_server_answer_empty
                    else if (it.isSuccessful){

                        val responseBody = it.body()!!

                        when (responseBody.status){
                            "1" -> {
                                userMessage = R.string.ad_was_updated
                                _adWasUpdatedLiveData.value = SingleEvent(true)
                            }

                            "0" -> userMessage = R.string.ad_could_not_be_created

                        }
                    }

                    userMessage?.let {
                        _userMessageLiveData.value = SingleEvent(it)
                    }
                }
            }
        }
    }

    fun deleteAd(idAd : Long) {

        viewModelScope.launch {

            withContext(Dispatchers.IO){
                apiInterface.deleteAd(idAd)

            }.let {

                var userMessage : Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful){

                    val responseBody = it.body()!!

                    when (responseBody.status){
                        "1" -> {
                            userMessage = R.string.ad_was_deleted
                            _adWasUpdatedLiveData.value = SingleEvent(true)
                        }

                        "0" -> userMessage = R.string.ad_could_not_be_deleted
                    }
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }
            }
        }
    }
}