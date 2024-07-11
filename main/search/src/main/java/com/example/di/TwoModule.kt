package com.example.di

import com.example.allTickets.data.AllTicketsRepositoryImpl
import com.example.allTickets.data.allTickets.AllTicketsRemoteSource
import com.example.allTickets.data.allTickets.remote.AllTicketsApi
import com.example.allTickets.data.allTickets.remote.AllTicketsSourceImpl
import com.example.allTickets.data.allTickets.remote.MockAllTickets
import com.example.allTickets.domain.allTickets.AllTicketsRepository
import com.example.choiceCountry.data.ChoiceCountryRepositoryImpl
import com.example.choiceCountry.data.choiceCountry.ChoiceCountryRemoteSource
import com.example.choiceCountry.data.choiceCountry.remote.ChoiceCountryApi
import com.example.choiceCountry.data.choiceCountry.remote.ChoiceCountrySourceImpl
import com.example.choiceCountry.data.choiceCountry.remote.MockChoiceCountry
import com.example.choiceCountry.domain.choiseCountry.ChoiceCountryRepository
import com.example.common.DispatchersProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module()
class TwoModule {
    @Provides
    fun provideAllTickets(
        api: AllTicketsApi,
        dispatchersProvider: DispatchersProvider,
    ): AllTicketsRemoteSource = AllTicketsSourceImpl(api, dispatchersProvider)

    @Provides
    fun provideAllTicketsApi(retrofit: Retrofit): AllTicketsApi = retrofit.create(AllTicketsApi::class.java)

    @Provides
    fun provideAllTicketsRepository(allTicketsRepositoryImpl: AllTicketsRepositoryImpl): AllTicketsRepository = allTicketsRepositoryImpl

    @Provides
    fun provideChoiceCountryScreenRepository(choiceScreenRepositoryImpl: ChoiceCountryRepositoryImpl): ChoiceCountryRepository =
        choiceScreenRepositoryImpl

    @Provides
    fun provideChoiceCountryApi(retrofit: Retrofit): ChoiceCountryApi =
        retrofit.create(
            ChoiceCountryApi::class.java,
        )

    @Provides
    fun provideChoiceCountryScreen(
        api: ChoiceCountryApi,
        dispatchersProvider: DispatchersProvider,
    ): ChoiceCountryRemoteSource = ChoiceCountrySourceImpl(api, dispatchersProvider)

    @Provides
    fun provideChoiceCountryRemoteSource(): ChoiceCountryRemoteSource = MockChoiceCountry()

    @Provides
    fun provideAllTicketsRemoteSource(): AllTicketsRemoteSource = MockAllTickets()
}
