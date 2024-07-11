package com.example.effectivemobile.di

import com.example.effectivemobile.R
import com.example.effectivemobile.navigation.RouterImpl
import com.example.navigation.Router
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object AppModule {
    @Provides
    @Named("Host")
    fun provideRouter(): Router = RouterImpl(R.id.NavHostFragment)

    @Provides
    @Named("Child")
    fun provideRouterChild(): Router = RouterImpl(R.id.childNavGraph)
}
