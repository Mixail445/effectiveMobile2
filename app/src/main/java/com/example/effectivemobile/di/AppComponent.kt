package com.example.effectivemobile.di

import com.example.effectivemobile.presentation.allTickets.AllTicketsFragment
import com.example.effectivemobile.presentation.bottomFragment.BottomFragment
import com.example.effectivemobile.presentation.choiceCountry.ChoiceCountryFragment
import com.example.effectivemobile.presentation.emptyFragment.EmptyFragment
import com.example.effectivemobile.presentation.mainscreen.MainFragment
import com.example.effectivemobile.presentation.mainscreen.dialog.DialogSearch
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(mainFragment: MainFragment)

    fun inject(allTicketsFragment: AllTicketsFragment)

    fun inject(bottomFragment: BottomFragment)

    fun inject(choiceCountryFragment: ChoiceCountryFragment)

    fun inject(search: DialogSearch)

    fun inject(emptyFragment: EmptyFragment)
}
