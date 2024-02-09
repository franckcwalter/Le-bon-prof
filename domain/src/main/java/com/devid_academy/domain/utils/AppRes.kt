package com.devid_academy.domain.utils

interface AppRes {
    fun getString(resourceId: Int): String
    fun getString(resourceId: Int, vararg args: String): String
}

