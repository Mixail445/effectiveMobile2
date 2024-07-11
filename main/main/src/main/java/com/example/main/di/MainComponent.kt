package com.example.main.di

import com.example.di.CoreComponent
import com.example.di.NetworkModule
import com.example.main.ui.mainscreen.MainFragment
import com.example.main.ui.mainscreen.dialog.DialogSearch
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, NetworkModule::class], dependencies = [CoreComponent::class])
interface MainComponent {
    @Component.Factory
    interface Factory {
        fun create(
            mainModule: MainModule,
            coreComponent: CoreComponent,
            networkModule: NetworkModule,
        ): MainComponent
    }

    fun inject(fragment: MainFragment)

    fun inject(dialogSearch: DialogSearch)
}
