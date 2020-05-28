package com.mvvmapp.di.component

import android.app.Application
import com.mvvmapp.MainApplication
import com.mvvmapp.di.module.ActivityModule
import com.mvvmapp.di.module.ApplicationModule
import com.mvvmapp.di.module.DatabaseModule
import com.mvvmapp.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)
}