package com.example.main.di

import android.content.Context
import com.example.common.DispatchersProvider
import com.example.common.SharedPreferenceUtil
import com.example.common.SharedPreferencesOne
import com.example.main.data.MainScreenRepositoryImpl
import com.example.main.data.mainscreen.MainRemoteSource
import com.example.main.data.mainscreen.remote.MainScreenApi
import com.example.main.data.mainscreen.remote.MainScreenSourceImpl
import com.example.main.data.mainscreen.remote.MockMainRemoteSource
import com.example.main.domain.mainScreen.MainScreenRepository
import com.example.main.ui.mainscreen.DataForSharedPreferences
import com.example.main.utils.SaveStringLocalSource
import com.example.main.utils.SaveStringLocalSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module()
class MainModule(
    private val context: Context,
) {
    @Provides
    fun provideMainScreenApi(retrofit: Retrofit): MainScreenApi = retrofit.create(MainScreenApi::class.java)

    @Provides
    fun provideMainScreenApiSource(
        api: MainScreenApi,
        dispatchersProvider: DispatchersProvider,
    ): MainRemoteSource = MainScreenSourceImpl(api, dispatchersProvider)

    @Provides
    fun provideMainScreenRepository(mainScreenRepository: MainScreenRepositoryImpl): MainScreenRepository = mainScreenRepository

    @Provides
    fun provideSharePref(): SharedPreferencesOne<DataForSharedPreferences> =
        SharedPreferenceUtil(
            context,
            DataForSharedPreferences::class.java,
        )

    @Provides
    @Singleton
    fun provideShare(sharedPreferencesOne: SharedPreferencesOne<DataForSharedPreferences>): SaveStringLocalSource =
        SaveStringLocalSourceImpl(
            sharedPreferencesOne,
        )

    @Singleton
    @Provides
    fun provideMock(): MainRemoteSource = MockMainRemoteSource()
}
