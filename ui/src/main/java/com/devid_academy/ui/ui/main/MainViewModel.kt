package com.devid_academy.projetfinal.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.AdDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.Role
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
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


    private var _adListLivedata = MutableLiveData<List<AdDto>>()
    val adListLivedata: LiveData<List<AdDto>> get() = _adListLivedata

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData: LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    init {
        fetchAds()
    }

    fun fetchAds(
        filterByMaxPrice: Int = 60,
        filterByFav : Boolean = false,
        filterByLocation : String = ""
    )
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                apiInterface.getAds(myPrefs.user_id)
            }.let {

                var userMessage: Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful) {

                    val responseBody = it.body()!!

                    _adListLivedata.value = responseBody.ads

                    if (filterByMaxPrice < 60 || filterByFav || filterByLocation.isNotEmpty())
                        filterAds(filterByMaxPrice, filterByFav, filterByLocation)
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }
            }
        }
    }

    private fun filterAds(
        filterByMaxPrice: Int = 60,
        filterByFav : Boolean = false,
        filterByLocation : String = ""
    )
    {
        adListLivedata.value?.filter {
            (!filterByFav || it.isFav == 1)
            && (filterByMaxPrice >= 60 || it.price.toDouble() <= filterByMaxPrice.toDouble())
            && (filterByLocation.isBlank() || it.location.lowercase().startsWith(filterByLocation.lowercase()))
                }?.let { _adListLivedata.value = it }
    }

    fun goToDetail(idAd: Long) {
        _navDirLiveData.value =
            SingleEvent(MainFragmentDirections.actionMainFragmentToAdDetailsFragment(idAd))
    }

    fun goToProfile() {
        when (myPrefs.user_role) {
            Role.ADMIN -> MainFragmentDirections.actionMainFragmentToAdminFragment()
            Role.TEACH -> MainFragmentDirections.actionMainFragmentToProfileTeacherFragment()
            Role.LEARN -> MainFragmentDirections.actionMainFragmentToProfileLearnerFragment()
            else -> {
                null
            }
        }?.let {
            _navDirLiveData.value = SingleEvent(it)
        }
    }
}