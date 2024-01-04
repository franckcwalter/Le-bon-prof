package com.devid_academy.projetfinal.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devid_academy.projetfinal.network.AdDto
import com.devid_academy.projetfinal.network.AdsDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.network.CreateAdDto
import com.devid_academy.projetfinal.network.CreateUserDto
import com.devid_academy.projetfinal.network.DeleteUserDto
import com.devid_academy.projetfinal.network.UpdateAdDto
import com.devid_academy.projetfinal.network.UpdateUserDto
import com.devid_academy.projetfinal.util.MyPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private var apiInterface : ApiInterface,
    private var myPrefs : MyPrefs
) : ViewModel()
{

    /*
    fun getStuffFromApi()  {

        viewModelScope.launch {
            withContext(Dispatchers.IO){

                    // apiInterface.deleteUser(DeleteUserDto(12))
                    // apiInterface.createUser(CreateUserDto("email","password","name",20))
                    // apiInterface.createAd(CreateAdDto("ref","title", "photo","desc","place","loc","10.12", "T543", 1,3))
                    // apiInterface.updateAd(UpdateAdDto(4,"ref","title", "photo","desc","place","loc","10.12", "T543", 1,3))

                apiInterface.getSubjects()
            }?.let {
                println(it.body())
            }
        }
    }
     */
}