package com.devid_academy.projetfinal.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.ui.ui.splash.SplashFragmentDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private var myPrefs: MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<NavDirections>()
    val navDirLiveData: LiveData<NavDirections> get() = _navDirLiveData

    init {
        navigateAfterDelay()
    }

    private fun navigateAfterDelay() {

        viewModelScope.launch {
            delay(3000)

            (if(myPrefs.user_id > 0 && myPrefs.user_role >= 10)
                SplashFragmentDirections.actionSplashFragmentToMainFragment()
            else SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                .let {
                    _navDirLiveData.value = it
                }
        }
    }
}