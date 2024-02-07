package com.devid_academy.ui

import com.devid_academy.projetfinal.ui.ad_details.AdDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {
    viewModel { AdDetailsViewModel(get()) }
}
