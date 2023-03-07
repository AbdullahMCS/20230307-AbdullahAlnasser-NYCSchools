package com.example.myapplication.di.modules

import com.example.myapplication.ui.fragments.DetailFragment
import com.example.myapplication.ui.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideDetailFragment(): DetailFragment
}