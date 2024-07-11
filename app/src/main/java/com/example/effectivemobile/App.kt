package com.example.effectivemobile

import android.app.Application
import com.example.di.CoreComponent
import com.example.di.CoreComponentProvider
import com.example.effectivemobile.di.AppComponent
import com.example.effectivemobile.di.DaggerAppComponent
import com.example.main.di.MainModule

class App :
    Application(),
    CoreComponentProvider {
    override lateinit var coreComponent: CoreComponent
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(mainModule = MainModule(applicationContext), application = this)
        coreComponent = appComponent
        appComponent.inject(this)
    }
}
