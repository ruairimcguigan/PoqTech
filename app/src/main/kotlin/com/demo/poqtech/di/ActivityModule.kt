package com.demo.poqtech.di

import com.demo.poqtech.allrepos.ReposActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector (modules = [FragmentModule::class])
    abstract fun contributesMainActivity(): ReposActivity
}