package com.devid_academy.ui.ui.profile_teacher

import com.devid_academy.domain.entities.ButtonLabel

data class ProfileTeacherUiState(

    val pageTitle: String = "",
    val adTitle: String = "",
    val adPrice: String = "",
    val adContent: String = "",
    val buttonLabel: Int = ButtonLabel.NO_LABEL.messageResId,
    val hasNoAd : Boolean = false

)
