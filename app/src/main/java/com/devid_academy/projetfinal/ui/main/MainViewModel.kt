package com.devid_academy.projetfinal.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.network.AdDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.Role
import com.devid_academy.projetfinal.util.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel() {


    private var _adList = MutableLiveData<List<AdDto>>()
    val adList : LiveData<List<AdDto>> get() = _adList

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    init {
        fetchAds()
    }

    fun fetchAds() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                apiInterface.getAds()
            }.let {

                var userMessage: Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful) {

                    val responseBody = it.body()!!

                    _adList.value = responseBody.ads
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }

            }
        }
    }

    fun goToDetail(idAd : Long){
        _navDirLiveData.value = SingleEvent(MainFragmentDirections.actionMainFragmentToAdDetailsFragment(idAd))
    }

    fun goToProfile() {
        when(myPrefs.user_role){
            Role.ADMIN -> MainFragmentDirections.actionMainFragmentToAdminFragment()
            Role.TEACH -> MainFragmentDirections.actionMainFragmentToProfileTeacherFragment(passAdDtoIfTeacherHasAd())
            Role.LEARN -> MainFragmentDirections.actionMainFragmentToProfileLearnerFragment()
            else -> { null }
        }?.let{
            _navDirLiveData.value = SingleEvent(it)
        }
    }

    fun passAdDtoIfTeacherHasAd() : AdDto? {

        return adList.value?.find{
            myPrefs.user_id == it.idUser
        }
    }
}