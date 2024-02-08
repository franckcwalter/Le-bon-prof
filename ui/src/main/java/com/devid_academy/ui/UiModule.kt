package com.devid_academy.ui

import com.devid_academy.projetfinal.ui.ad_create.AdCreateViewModel
import com.devid_academy.projetfinal.ui.ad_update.AdUpdateViewModel
import com.devid_academy.ui.ui.ad_details.AdDetailsViewModel
import com.devid_academy.ui.ui.login.LoginViewModel
import com.devid_academy.ui.ui.main.MainViewModel
import com.devid_academy.ui.ui.profile_teacher.ProfileTeacherViewModel
import com.devid_academy.ui.ui.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }

    viewModel { MainViewModel(get(), get(), get()) }
    viewModel { AdDetailsViewModel(get(), get()) }

    viewModel { ProfileTeacherViewModel(get(), get(), get()) }
    viewModel { AdCreateViewModel(get(), get()) }
    viewModel { AdUpdateViewModel(get(), get()) }

}
