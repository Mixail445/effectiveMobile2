package com.example.effectivemobile.di

import android.app.Application
import android.content.Context
import com.example.data.AllTicketsRepositoryImpl
import com.example.data.ChoiceCountryRepositoryImpl
import com.example.data.MainScreenRepositoryImpl
import com.example.data.repository.allTickets.AllTicketsRemoteSource
import com.example.data.repository.allTickets.remote.AllTicketsApi
import com.example.data.repository.allTickets.remote.AllTicketsSourceImpl
import com.example.data.repository.choiceCountry.ChoiceCountryRemoteSource
import com.example.data.repository.choiceCountry.remote.ChoiceCountryApi
import com.example.data.repository.choiceCountry.remote.ChoiceCountrySourceImpl
import com.example.data.repository.mainscreen.MainRemoteSource
import com.example.data.repository.mainscreen.remote.DispatchersProvider
import com.example.data.repository.mainscreen.remote.DispatchersProviderImpl
import com.example.data.repository.mainscreen.remote.MainScreenApi
import com.example.data.repository.mainscreen.remote.MainScreenSourceImpl
import com.example.effectivemobile.Constant.BASE_URL
import com.example.effectivemobile.R
import com.example.effectivemobile.presentation.common.Router
import com.example.effectivemobile.presentation.common.RouterImpl
import com.example.effectivemobile.presentation.common.SaveStringLocalSource
import com.example.effectivemobile.presentation.common.SaveStringLocalSourceImpl
import com.example.effectivemobile.presentation.common.SharedPreferenceUtil
import com.example.effectivemobile.presentation.common.SharedPreferencesOne
import com.example.effectivemobile.presentation.mainscreen.SaveDataInSharedPref
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class AppModule(
    private val context: Application,
) {
    @Provides
    fun provideMainScreenApi(retrofit: Retrofit): MainScreenApi = retrofit.create(MainScreenApi::class.java)

    @Provides
    fun provideChoiceCountryApi(retrofit: Retrofit): ChoiceCountryApi = retrofit.create(ChoiceCountryApi::class.java)

    @Provides
    fun provideAllTicketsApi(retrofit: Retrofit): AllTicketsApi = retrofit.create(AllTicketsApi::class.java)

    @Provides
    fun provideMainScreenApiSource(
        api: MainScreenApi,
        dispatchersProvider: DispatchersProvider,
    ): MainRemoteSource = MainScreenSourceImpl(api, dispatchersProvider)

    @Provides
    fun provideChoiceCountryScreen(
        api: ChoiceCountryApi,
        dispatchersProvider: DispatchersProvider,
    ): ChoiceCountryRemoteSource = ChoiceCountrySourceImpl(api, dispatchersProvider)

    @Provides
    fun provideAllTickets(
        api: AllTicketsApi,
        dispatchersProvider: DispatchersProvider,
    ): AllTicketsRemoteSource = AllTicketsSourceImpl(api, dispatchersProvider)

    @Provides
    @Named("Host")
    fun provideRouter(): Router = RouterImpl(R.id.NavHostFragment)

    @Provides
    @Named("Child")
    fun provideRouterChild(): Router = RouterImpl(R.id.childNavGraph)

    @Provides
    fun provideDispatcher(): DispatchersProvider = DispatchersProviderImpl

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    private val moshi =
        Moshi
            .Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

    private val interceptor =
        run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    fun provideSharePref(): SharedPreferencesOne<SaveDataInSharedPref> =
        SharedPreferenceUtil(
            context,
            SaveDataInSharedPref::class.java,
        )

    @Provides
    fun provideShare(sharedPreferencesOne: SharedPreferencesOne<SaveDataInSharedPref>): SaveStringLocalSource =
        SaveStringLocalSourceImpl(
            sharedPreferencesOne,
        )

    @Provides
    fun provideMainScreenRepository(mainScreenRepository: MainScreenRepositoryImpl): com.example.domain.mainScreen.MainScreenRepository =
        mainScreenRepository

    @Provides
    fun provideAllTicketsRepository(
        allTicketsRepositoryImpl: AllTicketsRepositoryImpl,
    ): com.example.domain.allTickets.AllTicketsRepository = allTicketsRepositoryImpl

    @Provides
    fun provideChoiceCountryScreenRepository(
        choiceScreenRepositoryImpl: ChoiceCountryRepositoryImpl,
    ): com.example.domain.choiseCountry.ChoiceCountryRepository = choiceScreenRepositoryImpl
}
