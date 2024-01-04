package com.devid_academy.projetfinal.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.Role
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    fun fetchAds(){
        /**
         * TODO : API CALL TO FETCH ADS
         * **/
    }

    fun goToDetail(idAd : Long){
        _navDirLiveData.value = SingleEvent(MainFragmentDirections.actionMainFragmentToAdDetailsFragment(idAd))
    }

    fun goToProfile() {
        when(myPrefs.user_role){
            Role.ADMIN -> MainFragmentDirections.actionMainFragmentToAdminFragment()
            Role.TEACH -> MainFragmentDirections.actionMainFragmentToProfileTeacherFragment()
            Role.LEARN -> MainFragmentDirections.actionMainFragmentToProfileLearnerFragment()
            else -> { null }
        }?.let{
            _navDirLiveData.value = SingleEvent(it)
        }
    }
}