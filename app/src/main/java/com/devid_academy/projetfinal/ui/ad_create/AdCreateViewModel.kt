package com.devid_academy.projetfinal.ui.ad_create

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.network.CreateAdDto
import com.devid_academy.projetfinal.ui.profile_teacher.ProfileTeacherFragmentDirections
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import com.squareup.moshi.Json
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AdCreateViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {


    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


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
            || price.isBlank()
            )
            _userMessageLiveData.value = SingleEvent(R.string.user_message_please_fill_out_all_fields)
        else {

            viewModelScope.launch {
                with(Dispatchers.IO){

                    apiInterface.createAd(CreateAdDto(
                        UUID.randomUUID().toString(),
                        title,
                        photo,
                        description,
                        place,
                        location,
                        price,
                        Calendar.getInstance().time.toString(),
                        0,
                        myPrefs.user_id))
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
                                 responseBody.id?.let {
                                     _navDirLiveData.value = SingleEvent(AdCreateFragmentDirections.actionAdCreateFragmentToProfileTeacherFragment(it))
                                 }
                                 userMessage = R.string.ad_was_created
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

    private fun goToProfileTeacher(idAd : Long){
    }
}