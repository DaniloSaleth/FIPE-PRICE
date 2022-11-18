package com.example.showmetheprice

import android.app.Application
import com.example.showmetheprice.di.repository
import com.example.showmetheprice.di.serviceModule
import com.example.showmetheprice.di.viewModel
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            if(BuildConfig.DEBUG){
                androidLogger()
            }
            androidContext(this@MyApp)
            modules(serviceModule,viewModel,repository)
        }
    }
}