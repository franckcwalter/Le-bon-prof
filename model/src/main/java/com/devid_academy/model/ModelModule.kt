package com.devid_academy.model

import android.content.Context
import com.devid_academy.domain.AppRes
import com.devid_academy.domain.usecases.CreateAdUseCase
import com.devid_academy.domain.usecases.DeleteAdUseCase
import com.devid_academy.domain.usecases.FetchAdDetailsByIdUseCase
import com.devid_academy.domain.usecases.FetchAdDetailsByUserIdUseCase
import com.devid_academy.domain.usecases.UpdateAdUseCase
import com.devid_academy.model.implementations.AppResImpl
import com.devid_academy.model.implementations.usecases.CreateAdUseCaseImpl
import com.devid_academy.model.implementations.usecases.DeleteAdUseCaseImpl
import com.devid_academy.model.implementations.usecases.FetchAdDetailsByIdUseCaseImpl
import com.devid_academy.model.implementations.usecases.FetchAdDetailsByUserIdUseCaseImpl
import com.devid_academy.model.implementations.usecases.UpdateAdUseCaseImpl
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
        MyPrefs(androidContext().getSharedPreferences("FwaJetpackFeedArticles", Context.MODE_PRIVATE))
    }

    single<AppRes>{
        AppResImpl(androidContext())
    }

    factory<FetchAdDetailsByIdUseCase> { FetchAdDetailsByIdUseCaseImpl(get(), get()) }
    factory<FetchAdDetailsByUserIdUseCase> { FetchAdDetailsByUserIdUseCaseImpl(get(), get()) }

    factory<CreateAdUseCase> { CreateAdUseCaseImpl(get()) }
    factory<UpdateAdUseCase> { UpdateAdUseCaseImpl(get()) }
    factory<DeleteAdUseCase> { DeleteAdUseCaseImpl(get()) }

}