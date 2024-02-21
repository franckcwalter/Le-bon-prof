package com.devid_academy.model.network

import com.devid_academy.domain.entities.AdDto
import com.devid_academy.domain.entities.AdsDto
import com.devid_academy.domain.entities.CreateAdDto
import com.devid_academy.domain.entities.CreateUserDto
import com.devid_academy.domain.entities.LoginUser
import com.devid_academy.domain.entities.ResponseCreateAdDto
import com.devid_academy.domain.entities.ResponseDto
import com.devid_academy.domain.entities.SubjectsDto
import com.devid_academy.domain.entities.UpdateAdDto
import com.devid_academy.domain.entities.UpdateUserDto
import com.devid_academy.domain.entities.UserDto
import com.devid_academy.domain.entities.UsersDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET(ApiRoutes.USER_GET_ALL)
    suspend fun getUsers()
    : Response<UsersDto>?

    @GET(ApiRoutes.USER_GET)
    suspend fun getUser(
        @Query("id") id : Long
    ) : Response<UserDto>?

    @FormUrlEncoded
    @POST(ApiRoutes.USER_LOGIN)
    suspend fun logInUser(
        @Field("email") email : String,
        @Field("password") password : String
    ) : Response<LoginUser>?


    @POST(ApiRoutes.USER_CREATE)
    suspend fun createUser(
        @Body createUser : CreateUserDto
    ) : Response<LoginUser> ?

    @POST(ApiRoutes.USER_UPDATE)
    suspend fun updateUser(
        @Body updateUser : UpdateUserDto
    ) : Response<ResponseDto> ?


    // changer pour Field
    @DELETE(ApiRoutes.USER_DELETE)
    suspend fun deleteUser(
        @Query("id") id : Long
    ) : Response<ResponseDto> ?




    @GET(ApiRoutes.AD_GET_ALL)
    suspend fun getAds(
        @Query("idUser") id : Long
    ) : Response<AdsDto>

    @GET(ApiRoutes.AD_GET_FROM_ID)
    suspend fun getAd(
        @Query("id") id : Long,
        @Query("idUser") idUser : Long
    ) : Response<AdDto> ?

    @GET(ApiRoutes.AD_GET_FROM_USER)
    suspend fun getAdByUserId(
        @Query("idUser") idUser : Long
    ) : Response<AdDto> ?



    @GET(ApiRoutes.AD_TOGGLE_FAV)
    suspend fun toggleFav(
        @Query("idAd") idAd : Long,
        @Query("idUser") idUser : Long
    ) : Response<ResponseDto> ?


    @POST(ApiRoutes.AD_CREATE)
    suspend fun createAd(
        @Body createAd : CreateAdDto
    ) : Response<ResponseCreateAdDto> ?

    @POST(ApiRoutes.AD_UPDATE)
    suspend fun updateAd(
        @Body updateAd : UpdateAdDto
    ) : Response<ResponseDto> ?


    // changer pour Field
    @DELETE(ApiRoutes.AD_DELETE)
    suspend fun deleteAd(
        @Query("id") id : Long
    ) : Response<ResponseDto> ?


    @GET(ApiRoutes.SUBJECT_GET_ALL)
    suspend fun getSubjects()
    : Response<SubjectsDto> ?


}