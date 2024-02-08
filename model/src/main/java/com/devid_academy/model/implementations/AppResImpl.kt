package com.devid_academy.model.implementations

import android.content.Context
import androidx.annotation.StringRes
import com.devid_academy.domain.AppRes

class AppResImpl(
    private val context: Context
) : AppRes {

    override fun getString(@StringRes resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getString(@StringRes resourceId: Int, vararg args: String): String {
        return context.getString(resourceId, *args)
    }
}
