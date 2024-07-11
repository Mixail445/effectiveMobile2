package com.example.di

import com.example.allTickets.ui.AllTicketsFragment
import com.example.choiceCountry.ui.ChoiceCountryFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TwoModule::class, NetworkModule::class], dependencies = [CoreComponent::class])
interface TwoModuleComponent {
    fun inject(fragment: AllTicketsFragment)

    fun inject(fragment: ChoiceCountryFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            networkModule: NetworkModule,
        ): TwoModuleComponent
    }
}
