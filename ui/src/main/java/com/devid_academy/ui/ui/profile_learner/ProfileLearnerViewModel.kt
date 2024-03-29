package com.devid_academy.ui.ui.profile_learner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.devid_academy.domain.utils.MyPrefs
import com.devid_academy.domain.utils.SingleEvent

class ProfileLearnerViewModel(private var myPrefs: MyPrefs) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData


    fun logOutUser() {

        myPrefs.user_id = 0
        myPrefs.user_role = 0

       _navDirLiveData.value = SingleEvent(ProfileLearnerFragmentDirections.actionProfileLearnerFragmentToLoginFragment())

    }
}
