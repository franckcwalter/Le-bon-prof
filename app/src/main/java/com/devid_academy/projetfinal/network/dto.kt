package com.devid_academy.projetfinal.network

import com.squareup.moshi.Json

data class UserDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "email")
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "idRole")
    val idRole: Int
)

data class UsersDto(
    @Json(name = "users")
    val users: List<UserDto>,
    @Json(name = "itemCount")
    val itemCount: Int
)

data class CreateUserDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "idRole")
    val idRole: Long
)

data class UpdateUserDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "password")
    val password: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "idRole")
    val idRole: Int
)


data class DeleteUserDto(
    @Json(name = "id")
    val id : Long
)


data class AdsDto(
    @Json(name = "ads")
    val ads: List<AdDto>,
    @Json(name = "itemCount")
    val itemCount: Int
)

data class AdDto(
    @Json(name = "ad_reference")
    val adReference: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "place")
    val place: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "approved")
    val approved: Int,
    @Json(name = "first_name")
    val firstName: String,
)


data class CreateAdDto(
    @Json(name = "ad_reference")
    val adReference: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "place")
    val place: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "approved")
    val approved: Int,
    @Json(name = "idUser")
    val idUser: Long,
)


data class UpdateAdDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "ad_reference")
    val adReference: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "photo")
    val photo: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "place")
    val place: String,
    @Json(name = "location")
    val location: String,
    @Json(name = "price")
    val price: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "approved")
    val approved: Int,
    @Json(name = "idUser")
    val idUser: Long,
)


data class ResponseDto(
    @Json(name = "status")
    val status: String
)




data class SubjectsDto(
    @Json(name = "subjects")
    val subjects: List<SubjectDto>,
    @Json(name = "itemCount")
    val itemCount: Int
)

data class SubjectDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String
)