package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.LogOutUserUseCase
import com.devid_academy.domain.utils.MyPrefs

class LogOutUserUseCaseImpl(
    private val myPrefs: MyPrefs
) : LogOutUserUseCase {
    override fun logoutUser() {
        myPrefs.user_id = 0
        myPrefs.user_role = 0
    }
}