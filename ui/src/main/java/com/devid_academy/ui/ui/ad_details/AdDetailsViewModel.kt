package com.devid_academy.projetfinal.ui.ad_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.FetchAdDetailsUseCase
import com.devid_academy.projetfinal.network.AdDto
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs
import com.devid_academy.projetfinal.util.SingleEvent
import com.devid_academy.ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import javax.inject.Inject

class AdDetailsViewModel (private val fetchAdDetail : FetchAdDetailsUseCase)
    : ViewModel()
{

    private var _adLiveData = MutableLiveData<AdDto>()
    val adLiveData : LiveData<AdDto> get() = _adLiveData

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData : LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData : LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    fun fetchArticle(id: Long){

        viewModelScope.launch {
            fetchAdDetail.fetchAdDetails(id)?.let {
                it.let {
                    _adLiveData.value = it
                }

                fetchAdDetail.errorMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it!!)
                }
            }
        }
    }

    /*
    fun fetchArticle(id : Long){

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                apiInterface.getAd(id, myPrefs.user_id)

            }.let {

                var userMessage: Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful) {
                    val responseBody = it.body()!!
                    _adLiveData.value = responseBody
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }
            }
        }
    } */

    /*
    fun toggleFav(id : Long){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                apiInterface.toggleFav(id, myPrefs.user_id)

            }.let {

                var userMessage: Int? = null

                if (it == null)
                    userMessage = R.string.user_message_no_server_answer
                else if (it.body() == null)
                    userMessage = R.string.user_message_server_answer_empty
                else if (it.isSuccessful) {

                    val responseBody = it.body()!!

                    when (responseBody.status) {
                        "1" -> {
                            userMessage = R.string.fav_status_modified
                            fetchArticle(id)
                        }

                        "0" -> userMessage = R.string.fav_status_not_modified

                    }
                }

                userMessage?.let {
                    _userMessageLiveData.value = SingleEvent(it)
                }
            }
        }
    } */
}





