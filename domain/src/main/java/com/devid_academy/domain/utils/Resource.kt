package com.devid_academy.domain.utils

sealed class Resource<T> (val data : T? = null, val errorMessage : Int? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: Int, data: T? = null) : Resource<T>(data, message)
}

