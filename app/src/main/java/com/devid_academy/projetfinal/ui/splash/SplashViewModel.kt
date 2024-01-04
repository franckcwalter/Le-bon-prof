package com.devid_academy.projetfinal.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private var myPrefs : MyPrefs
) : ViewModel() {

    private var _navDirLiveData = MutableLiveData<NavDirections>()
    val navDirLiveData : LiveData<NavDirections> get() = _navDirLiveData

    init { navigateAfterDelay() }

    private fun navigateAfterDelay(){

        viewModelScope.launch {
            delay(2000)

            (if(myPrefs.user_id > 0 && myPrefs.user_role >= 10)/*!myPrefs.token.isNullOrEmpty() &&*/
                SplashFragmentDirections.actionSplashFragmentToLoginFragment()
            else SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                .let {
                    _navDirLiveData.value = it
                }
        }
    }

}