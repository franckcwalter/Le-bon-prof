package com.devid_academy.ui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.Role
import com.devid_academy.domain.usecases.FetchAdsUseCase
import com.devid_academy.domain.usecases.FilterAdsUseCase
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import kotlinx.coroutines.launch

class MainViewModel(
    private var fetchAdsUseCase: FetchAdsUseCase,
    private var filterAdsUseCase: FilterAdsUseCase,
    private var myPrefs: MyPrefs
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

            fetchAdsUseCase.fetchAds().let {

                when(it){
                    is Resource.Success -> {
                        it.data?.ads?.let { adList ->

                            _adListLivedata.value = adList

                            if (filterByMaxPrice < 60 || filterByFav || filterByLocation.isNotEmpty()) {
                                filterAdsUseCase.filterAds(
                                    adList,
                                    filterByMaxPrice,
                                    filterByFav,
                                    filterByLocation
                                ).let { filteredAdList ->
                                    _adListLivedata.value = filteredAdList
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        it.errorMessage?.let {errorMessage ->
                            _userMessageLiveData.value = SingleEvent(errorMessage)
                        }
                    }
                }
            }
        }
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