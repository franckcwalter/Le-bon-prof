package com.devid_academy.ui.ui.ad_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.InfoMessage
import com.devid_academy.domain.usecases.FetchAdDetailsByIdUseCase
import com.devid_academy.domain.usecases.ToggleFavUseCase
import com.devid_academy.domain.utils.Resource
import com.devid_academy.domain.utils.SingleEvent
import kotlinx.coroutines.launch

class AdDetailsViewModel(
    private val fetchAdDetailUseCase: FetchAdDetailsByIdUseCase,
    private val toggleFavUseCase: ToggleFavUseCase
) : ViewModel() {

    private var _adLiveData = MutableLiveData<AdDto>()
    val adLiveData: LiveData<AdDto> get() = _adLiveData

    private var _navDirLiveData = MutableLiveData<SingleEvent<NavDirections>>()
    val navDirLiveData: LiveData<SingleEvent<NavDirections>> get() = _navDirLiveData

    private var _userMessageLiveData = MutableLiveData<SingleEvent<Int>>()
    val userMessageLiveData: LiveData<SingleEvent<Int>> get() = _userMessageLiveData


    fun fetchAd(id: Long) {
        viewModelScope.launch {
            fetchAdDetailUseCase.fetchAdDetailsById(id)?.let {

                when(it){
                    is Resource.Success -> {
                        it.data?.let {
                            _adLiveData.value = it
                        }
                    }
                    is Resource.Error -> {

                        it.errorMessage?.let {
                            _userMessageLiveData.value = SingleEvent(it)
                        }
                    }
                }
            }
        }
    }


    fun toggleFav(id: Long) {
        viewModelScope.launch {

            toggleFavUseCase.toggleFav(id)?.let {
                when(it){
                    is Resource.Success -> {
                        fetchAd(id)
                        _userMessageLiveData.value = SingleEvent(InfoMessage.FAV_STATUS_MODIFIED.messageResId)
                    }
                    is Resource.Error -> {
                        it.errorMessage?.let {
                            _userMessageLiveData.value = SingleEvent(it)
                        }
                    }
                }
            }
        }
    }

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





