package com.example.di

import com.example.navigation.Router
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

interface CoreComponent {
    @Named("Host")
    fun provideRouter(): Router

    @Named("Child")
    fun provideRouterChild(): Router
}

interface CoreComponentProvider {
    val coreComponent: CoreComponent
}

@Singleton
class CoreComponentImpl
    @Inject
    constructor(
        @Named("Host") private val hostRouter: Router,
        @Named("Child") private val childRouter: Router,
    ) : CoreComponent {
        override fun provideRouter(): Router = hostRouter

        override fun provideRouterChild(): Router = childRouter
    }
