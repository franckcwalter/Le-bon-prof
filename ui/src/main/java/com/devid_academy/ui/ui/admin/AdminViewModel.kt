package com.devid_academy.projetfinal.ui.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.ui.util.SingleEvent


class AdminViewModel(
    private var apiInterface: ApiInterface,
    private var myPrefs: MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


}