package com.devid_academy.projetfinal

import android.app.Application
import com.devid_academy.model.di.modelModule
import com.devid_academy.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

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
