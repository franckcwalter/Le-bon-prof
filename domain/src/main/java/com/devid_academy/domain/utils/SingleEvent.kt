package com.devid_academy.domain.utils

class SingleEvent<out T>(private val content: T) {

    private var hasBeenHandeled = false

    fun getContentIfNotHandeled(): T? {
        return if (hasBeenHandeled) {
            null
        } else {
            hasBeenHandeled = true
            content
        }
    }
}
