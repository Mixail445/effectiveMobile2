package com.example.effectivemobile.di

import android.app.Application
import com.example.di.CoreComponent
import com.example.di.TwoModule
import com.example.effectivemobile.presentation.BottomFragment
import com.example.effectivemobile.presentation.MainActivity
import com.example.main.di.MainModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules =
        [
            AppModule::class,
            MainModule::class,
            TwoModule::class,
        ],
)
@Singleton
interface AppComponent : CoreComponent {
    fun inject(application: Application)

    fun inject(mainActivity: MainActivity)

    fun inject(bottomFragment: BottomFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            mainModule: MainModule,
        ): AppComponent
    }
}
