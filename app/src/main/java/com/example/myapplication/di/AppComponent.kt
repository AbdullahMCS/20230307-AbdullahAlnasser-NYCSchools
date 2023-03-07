package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.MyNycApp
import com.example.myapplication.di.modules.ActivityBindingModule
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.modules.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (AppModule::class),
    (ActivityBindingModule::class), (FragmentBindingModule::class)])
interface AppComponent: AndroidInjector<MyNycApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    fun inject(app: Application)
}