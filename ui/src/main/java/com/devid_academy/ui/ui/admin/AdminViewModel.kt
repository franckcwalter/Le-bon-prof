package com.devid_academy.ui.ui.admin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.devid_academy.domain.utils.SingleEvent


class AdminViewModel() : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


}