package com.devid_academy.domain.usecases

interface LogInUserUseCase {

    var userWasLoggedIn: Boolean

    suspend fun logInUser(email: String, password: String): Int?

}