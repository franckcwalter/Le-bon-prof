package com.devid_academy.model.implementations.usecases

import com.devid_academy.domain.usecases.GetUsernameUseCase
import com.devid_academy.domain.utils.MyPrefs

class GetUsernameUseCaseImpl(
    private val myPrefs: MyPrefs
) : GetUsernameUseCase {
    override fun getUsername(): String? {
        return myPrefs.user_name
    }
}