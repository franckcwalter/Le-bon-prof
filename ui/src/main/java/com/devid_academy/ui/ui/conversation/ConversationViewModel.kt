package com.devid_academy.projetfinal.ui.conversation

import androidx.lifecycle.ViewModel
import com.devid_academy.projetfinal.network.ApiInterface
import com.devid_academy.projetfinal.util.MyPrefs

class ConversationViewModel(
    private var apiInterface: ApiInterface,
    private var myPrefs: MyPrefs
) : ViewModel() {

}