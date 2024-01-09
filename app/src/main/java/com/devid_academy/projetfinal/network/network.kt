package com.devid_academy.projetfinal.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

object ApiRoutes {
    const val BASE_URL = "https://fwadevidfinalproject.alwaysdata.net/api/"

    const val USER_GET_ALL = "user/get-all.php"
    const val USER_GET = "user/get.php"
    const val USER_LOGIN = "user/login.php"
    const val USER_UPDATE = "user/update.php"
    const val USER_DELETE = "user/delete.php"
    const val USER_CREATE = "user/create.php"

    const val AD_GET_ALL = "ad/get-all.php"
    const val AD_GET = "ad/get.php"
    const val AD_UPDATE = "ad/update.php"
    const val AD_DELETE = "ad/delete.php"
    const val AD_CREATE = "ad/create.php"
    const val AD_TOGGLE_FAV = "ad/togglefav.php"

    const val SUBJECT_GET_ALL = "subject/get-all.php"

}

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
    ): Response<LoginUser>?


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
    )
    : Response<AdsDto> ?

    @GET(ApiRoutes.AD_GET)
    suspend fun getAd(
        @Query("id") id : Long,
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
    )
    : Response<ResponseCreateAdDto> ?

    @POST(ApiRoutes.AD_UPDATE)
    suspend fun updateAd(
        @Body updateAd : UpdateAdDto
    )
    : Response<ResponseDto> ?


    // changer pour Field
    @DELETE(ApiRoutes.AD_DELETE)
    suspend fun deleteAd(
        @Query("id") id : Long
    )
    : Response<ResponseDto> ?



    @GET(ApiRoutes.SUBJECT_GET_ALL)
    suspend fun getSubjects()
    : Response<SubjectsDto> ?


}