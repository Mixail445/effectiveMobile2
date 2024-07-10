package com.example.effectivemobile

import android.app.Application
import android.content.Context
import com.example.effectivemobile.di.AppComponent
import com.example.effectivemobile.di.AppModule
import com.example.effectivemobile.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}

val Context.appComponent: AppComponent
    get() =
        when (this) {
            is App -> appComponent
            else -> applicationContext.appComponent
        }
