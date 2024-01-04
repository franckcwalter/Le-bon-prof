package com.devid_academy.projetfinal.ui.profile_teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileTeacherViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel(){



    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


    fun goToCreateOrUpdateAd() {

        (if (/** teacher has an ad **/ true )
         SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdUpdateFragment2())
                else SingleEvent(ProfileTeacherFragmentDirections.actionProfileTeacherFragmentToAdCreateFragment()))
            .let {
                _navDirLiveData.value = it
            }
    }

}