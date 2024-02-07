package com.devid_academy.projetfinal

import android.app.Application
import com.devid_academy.model.modelModule
import com.devid_academy.ui.uiModule
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

@HiltAndroidApp
class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(modelModule, uiModule)
        }
    }
}
