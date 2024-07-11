package com.example.di

import android.content.Context
import com.example.Constant.BASE_URL
import com.example.common.DispatchersProvider
import com.example.common.DispatchersProviderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@Module
class NetworkModule
    @Inject
    constructor(
        private val context: Context,
    ) {
        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder().build()

        private val moshi =
            Moshi
                .Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        @Provides
        fun provideDispatcher(): DispatchersProvider = DispatchersProviderImpl

        @Provides
        fun provideContext(): Context = context

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
    }
