package com.demo.poqtech.di

import com.demo.poqtech.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector (modules = [FragmentModule::class])
    abstract fun contributesMainActivity(): MainActivity
}