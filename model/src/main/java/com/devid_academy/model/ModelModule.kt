package com.devid_academy.model

import android.content.Context
import com.devid_academy.domain.FetchAdDetailsUseCase
import com.devid_academy.projetfinal.util.MyPrefs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val modelModule = module {

    single<ApiInterface> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val moshi = Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(ApiRoutes.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    single<MyPrefs> {
        val sharedPreferences = androidContext().getSharedPreferences("FwaJetpackFeedArticles", Context.MODE_PRIVATE)
        MyPrefs(sharedPreferences)
    }

    factory<FetchAdDetailsUseCase> { FetchAdDetailsUseCaseImpl(get(), get()) }

}